package Himanshu;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Properties;

public class AIProject {
    
    private StanfordCoreNLP pipeline;

    public void Chatbot() {
        // Set up Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    // Method to analyze the user's input
    public String getResponse(String input) {
        // Create an empty Annotation with the user's input
        Annotation document = new Annotation(input);
        
        // Annotate the document
        ((Object) pipeline).annotate(document);

        // Extract sentiment from the input
        String sentiment = extractSentiment(document);

        // Generate a basic response based on sentiment
        if (sentiment.equals("Positive")) {
            return "That's great! Tell me more!";
        } else if (sentiment.equals("Negative")) {
            return "I'm sorry to hear that. What happened?";
        } else {
            return "Interesting! Can you elaborate?";
        }
    }

    // Method to extract sentiment from the input
    private String extractSentiment(Annotation document) {
        // Extract the sentences from the document
        Object sentences = ((Properties) document).get(CoreAnnotations.SentencesAnnotation.class);
        
        // For simplicity, let's just analyze the first sentence's sentiment
        for (CoreMap sentence : sentences) {
            // Get the sentiment of the sentence
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            return sentiment;
        }
        return "Neutral"; // Default sentiment if no sentence is found
    }
}
