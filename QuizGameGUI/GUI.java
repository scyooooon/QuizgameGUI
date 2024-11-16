package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {

    private JPanel contentPane;
    private JTextArea questionArea; // 질문을 표시하는 텍스트 영역
    private JTextField answerField; // 사용자가 답변을 입력하는 텍스트 필드
    private JButton submitButton; // 답변 제출 버튼
    private JTextArea feedbackArea; // 사용자의 답변에 대한 피드백(정답/오답)을 누적 표시하는 텍스트 영역

    // 퀴즈 문제와 정답 배열
    private String[] questions = {
            "Question 1:\nFill in the blank with the correct answer.\nThe __________ is a software interface between the application layer and the transport layer protocol.",
            "Question 2:\nFill in the blank with the correct answer.\nThe __________ refers to the connection between a host/router and a physical link.",
            "Question 3:\nWrite the bit length of IPv6.",
            "Question 4:\nTrue/False\nHTTP 1.1 maintains user states, while HTTP 1.0 is a stateless protocol.",
            "Question 5:\nTrue/False\nNon-persistent HTTP uses UDP, whereas persistent HTTP uses TCP."
    };

    private String[] answers = {"socket", "interface", "128", "False", "False"};
    private int currentQuestionIndex = 0; // 현재 진행중인 질문의 번호
    private int score = 0; // 사용자의 점수

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI frame = new GUI(); // GUI 객체 생성
                frame.showStartPanel(); // 시작 패널 표시
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


     // GUI 구성
    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        questionArea = new JTextArea();
        questionArea.setEditable(false); // 수정 불가능하게 설정
        questionArea.setLineWrap(true); // 텍스트 줄바꿈 활성화
        questionArea.setWrapStyleWord(true); // 단어 단위로 줄바꿈
        questionArea.setFont(new Font("Arial", Font.BOLD, 13)); // 기본 폰트 설정
        JScrollPane scrollPane = new JScrollPane(questionArea); // 스크롤 가능하게 설정
        contentPane.add(scrollPane, BorderLayout.CENTER); // 중앙에 배치

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        
        JLabel answerLabel = new JLabel("답안을 입력하세요:");
        inputPanel.add(answerLabel, BorderLayout.NORTH); 
        
        answerField = new JTextField();
        submitButton = new JButton("Submit"); // 제출 버튼 생성
        submitButton.setBackground(Color.BLUE); // 버튼 배경색 파란색
        submitButton.setForeground(Color.WHITE); // 버튼 글자색 흰색
        inputPanel.add(answerField, BorderLayout.CENTER); // 텍스트 필드를 중앙에 배치
        inputPanel.add(submitButton, BorderLayout.EAST); // 버튼을 오른쪽에 배치
        contentPane.add(inputPanel, BorderLayout.SOUTH); // 하단에 패널 추가
        
        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false); // 수정 불가능하게 설정
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        JScrollPane feedbackScroll = new JScrollPane(feedbackArea); // 스크롤 가능하게 설정
        feedbackScroll.setPreferredSize(new Dimension(200, 100)); // 크기 지정
        
        contentPane.add(feedbackScroll, BorderLayout.EAST); // 오른쪽에 배치

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSubmission();
            }
        });

        showNextQuestion();

    }
    private void showStartPanel() {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton startButton = new JButton("게임 시작");
        JButton exitButton = new JButton("종료");
        
        // 버튼 색상 설정
        startButton.setBackground(Color.RED); // 게임 시작 버튼 빨간색
        startButton.setForeground(Color.WHITE); // 글자색 흰색

        exitButton.setBackground(Color.BLUE); // 종료 버튼 파란색
        exitButton.setForeground(Color.WHITE); // 글자색 흰색

        startButton.addActionListener(e -> {
            setContentPane(contentPane); // 메인 패널로 전환
            revalidate();
            repaint();
            showNextQuestion(); // 첫 번째 질문 표시
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "게임을 종료합니다.", "Quiz Game", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // 프로그램 종료
        });

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);
        startPanel.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(startPanel); // 시작 패널 설정
        setVisible(true); // 창 표시
    }
    //사용자의 답변을 처리
    private void handleAnswerSubmission() {
        String userAnswer = answerField.getText().trim(); // 사용자가 입력한 답변 가져오기
        if (userAnswer.isEmpty()) { // 답변이 비어있는 경우
            JOptionPane.showMessageDialog(this, "Please enter an answer.", "Warning", JOptionPane.WARNING_MESSAGE);
            return; // 처리 중단
        }

        String correctAnswer = answers[currentQuestionIndex]; // 현재 질문의 정답 가져오기
        if (userAnswer.equalsIgnoreCase(correctAnswer)) { // 정답 확인
            feedbackArea.append(questions[currentQuestionIndex] + "\nYour Answer: " + userAnswer + "\nFeedback: Correct!\n\n");
            score += 20; // 정답일 경우 20씩 점수 증가
        } else {
            feedbackArea.append(questions[currentQuestionIndex] + "\nYour Answer: " + userAnswer + "\nFeedback: Incorrect.\n\n");
        }

        currentQuestionIndex++; // 다음 질문으로 이동
        answerField.setText(""); // 입력 필드 초기화

        if (currentQuestionIndex < questions.length) {
            showNextQuestion();
        } else {
            showFinalScore();
        }
    }

    private void showNextQuestion() {
        questionArea.setText(questions[currentQuestionIndex]);
    }

    private void showFinalScore() {
        questionArea.setText("Quiz finished!\nYour final score is: " + score); // 최종 점수 표시
        answerField.setEnabled(false); // 입력 필드 비활성화
        submitButton.setEnabled(false); // 버튼 비활성화
    }
  }



