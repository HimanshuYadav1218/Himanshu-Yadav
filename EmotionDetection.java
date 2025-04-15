package Himanshu;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EmotionDetection extends JFrame {
    private JLabel cameraScreen;
    private VideoCapture capture;
    private Mat frame;
    private CascadeClassifier faceCascade;
    private CascadeClassifier eyeCascade;
    private boolean isRunning;

    public EmotionDetection() {
        // Load OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        // Initialize window
        setTitle("Emotion Detection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Initialize camera screen
        cameraScreen = new JLabel();
        add(cameraScreen);
        
        // Load cascade classifiers
        faceCascade = new CascadeClassifier();
        faceCascade.load("haarcascade_frontalface_default.xml");
        eyeCascade = new CascadeClassifier();
        eyeCascade.load("haarcascade_eye.xml");
        
        // Initialize video capture
        capture = new VideoCapture(0);
        frame = new Mat();
        
        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    public void startCamera() {
        isRunning = true;
        Thread cameraThread = new Thread(() -> {
            while (isRunning) {
                if (capture.read(frame)) {
                    // Convert frame to grayscale
                    Mat grayFrame = new Mat();
                    Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
                    
                    // Detect faces
                    MatOfRect faces = new MatOfRect();
                    faceCascade.detectMultiScale(grayFrame, faces);
                    
                    // Process each detected face
                    for (Rect rect : faces.toArray()) {
                        // Draw rectangle around face
                        Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0), 2);
                        
                        // Extract face region
                        Mat faceROI = grayFrame.submat(rect);
                        
                        // Detect eyes
                        MatOfRect eyes = new MatOfRect();
                        eyeCascade.detectMultiScale(faceROI, eyes);
                        
                        // Draw rectangles around eyes
                        for (Rect eyeRect : eyes.toArray()) {
                            Point eyeCenter = new Point(
                                rect.x + eyeRect.x + eyeRect.width/2,
                                rect.y + eyeRect.y + eyeRect.height/2
                            );
                            int radius = (int) Math.round((eyeRect.width + eyeRect.height) * 0.25);
                            Imgproc.circle(frame, eyeCenter, radius, new Scalar(255, 0, 0), 2);
                        }
                        
                        // Analyze facial features for emotion
                        analyzeEmotion(faceROI, rect);
                    }
                    
                    // Display the frame
                    Image image = matToBufferedImage(frame);
                    cameraScreen.setIcon(new ImageIcon(image));
                }
            }
        });
        cameraThread.start();
    }

    private void analyzeEmotion(Mat faceROI, Rect faceRect) {
        // Simple emotion detection based on facial features
        // This is a basic implementation - real emotion detection would require
        // machine learning models and more sophisticated analysis
        
        Mat sobel = new Mat();
        Imgproc.Sobel(faceROI, sobel, -1, 1, 1);
        
        Core.MinMaxLocResult minMax = Core.minMaxLoc(sobel);
        double intensity = minMax.maxVal;
        
        String emotion = "Neutral";
        if (intensity > 100) {
            emotion = "Happy";
        } else if (intensity > 50) {
            emotion = "Sad";
        }
        
        // Draw emotion text above face rectangle
        Point textPoint = new Point(faceRect.x, faceRect.y - 10);
        Imgproc.putText(frame, emotion, textPoint, 
                       Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, 
                       new Scalar(255, 255, 255), 2);
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        
        return image;
    }

    public void stopCamera() {
        isRunning = false;
        if (capture.isOpened()) {
            capture.release();
        }
    }

    public static void main(String[] args) {
        EmotionDetection detector = new EmotionDetection();
        detector.setVisible(true);
        detector.startCamera();
        
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            detector.stopCamera();
        }));
    }
}