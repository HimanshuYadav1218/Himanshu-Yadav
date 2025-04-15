	package Himanshu;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.face.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;
import org.opencv.highgui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class FaceDetectionAndRecognition {

    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    private static CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");

    public static void main(String[] args) {
        // Set up a video capture from the webcam
        VideoCapture capture = new VideoCapture(0);

        if (!capture.isOpened()) {
            System.out.println("Error: Could not open video capture.");
            return;
        }

        // Create a face recognizer using Eigenfaces or Fisherfaces
        FaceRecognizer faceRecognizer = createEigenFaceRecognizer();

        // Store the faces for training (Use actual data in real applications)
        List<Mat> images = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();

        // Train with sample data (in practice, this would be real data)
        trainFaceRecognizer(faceRecognizer, images, labels);

        // Create a window to display video stream
        JFrame frame = new JFrame("Face Recognition");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Mat frameMat = new Mat();
        while (true) {
            capture.read(frameMat);  // Capture each frame

            if (frameMat.empty()) {
                System.out.println("Error: Empty frame.");
                break;
            }

            // Detect faces in the frame
            detectAndDisplay(frameMat, faceRecognizer, label);
        }

        capture.release();
    }

    // Method to detect and display face with recognition
    private static void detectAndDisplay(Mat frame, FaceRecognizer faceRecognizer, JLabel label) {
        Mat grayscaleImage = new Mat();
        Imgproc.cvtColor(frame, grayscaleImage, Imgproc.COLOR_BGR2GRAY);  // Convert to grayscale

        // Detect faces
        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(grayscaleImage, faces);

        // Draw rectangles and recognize faces
        for (Rect face : faces.toArray()) {
            Imgproc.rectangle(frame, face.tl(), face.br(), new Scalar(0, 255, 0), 3); // Draw rectangle
            int labelIndex = faceRecognizer.predict(grayscaleImage);  // Predict the label of the detected face

            // Show the label of the detected face
            String labelText = "Face: " + labelIndex;
            Imgproc.putText(frame, labelText, new Point(face.x, face.y - 10), Imgproc.FONT_HERSHEY_SIMPLEX, 1, new Scalar(255, 0, 0), 2);

        }

        // Convert Mat to ImageIcon to display in JFrame
        ImageIcon imageIcon = new ImageIcon(matToBufferedImage(frame));
        label.setIcon(imageIcon);
    }

    // Method to train the face recognizer with sample data (e.g., images and labels)
    private static void trainFaceRecognizer(FaceRecognizer faceRecognizer, List<Mat> images, List<Integer> labels) {
        // Load some sample data here (in practice, you would load real image data and labels)
        // Example: images.add(imread("path_to_image.jpg"));

        // Train the recognizer
        faceRecognizer.train(images, labels);
        faceRecognizer.save("trained_model.xml");
    }

    // Method to convert OpenCV Mat to BufferedImage for GUI display
    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), buffer);
        return image;
    }

    // Method to create a face recognizer (Eigenfaces)
    private static FaceRecognizer createEigenFaceRecognizer() {
        return EigenFaceRecognizer.create();
    }
}
