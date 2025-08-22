import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Morse extends JFrame {
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton toMorseBtn, toTextBtn;

    private static final HashMap<Character, String> textToMorse = new HashMap<>();
    private static final HashMap<String, Character> morseToText = new HashMap<>();

    // Static block to fill maps
    static {
        String[][] codes = {
            {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
            {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
            {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
            {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
            {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
            {"Z", "--.."},
            {"0", "-----"}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"}, {"4", "....-"},
            {"5", "....."}, {"6", "-...."}, {"7", "--..."}, {"8", "---.."}, {"9", "----."}
        };
        for (String[] pair : codes) {
            textToMorse.put(pair[0].charAt(0), pair[1]);
            morseToText.put(pair[1], pair[0].charAt(0));
        }
    }

    public Morse() {
        super("Morse Code Translator:");

        setSize(new Dimension(600, 500));
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#264653"));
        setLocationRelativeTo(null);

        addGUIComponents();
    }

    private void addGUIComponents() {
        JLabel titleLabel = new JLabel("Morse Code Translator");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(0, 10, 600, 40);
        add(titleLabel);

        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane scrollInput = new JScrollPane(inputArea);
        scrollInput.setBounds(50, 70, 500, 100);
        add(scrollInput);

        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        JScrollPane scrollOutput = new JScrollPane(outputArea);
        scrollOutput.setBounds(50, 250, 500, 100);
        add(scrollOutput);

        toMorseBtn = new JButton("Translate to Morse");
        toMorseBtn.setBounds(50, 190, 200, 40);
        add(toMorseBtn);

        toTextBtn = new JButton("Translate to Text");
        toTextBtn.setBounds(350, 190, 200, 40);
        add(toTextBtn);

        // Button Actions
        toMorseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = inputArea.getText().toUpperCase();
                outputArea.setText(textToMorse(text));
            }
        });

        toTextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String morse = inputArea.getText().trim();
                outputArea.setText(morseToText(morse));
            }
        });
    }

    // Convert Text → Morse
    private String textToMorse(String text) {
        StringBuilder morse = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                morse.append("   "); // 3 spaces between words
            } else if (textToMorse.containsKey(c)) {
                morse.append(textToMorse.get(c)).append(" ");
            }
        }
        return morse.toString().trim();
    }

    // Convert Morse → Text
    private String morseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] words = morse.split("   "); // words separated by 3 spaces
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                if (morseToText.containsKey(letter)) {
                    text.append(morseToText.get(letter));
                }
            }
            text.append(" ");
        }
        return text.toString().trim();
    }
}

