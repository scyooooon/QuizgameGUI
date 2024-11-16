client-server 퀴즈 게임을 GUI로 구현하였다.
주요 기능
1. 게임 시작 및 종료:
   "게임 시작" 버튼을 누르면 첫번째 질문 화면으로 이동
   "종료" 버튼을 누르면 프로그램 종료
2. 질문 표시
   질문은 questionArea에 표시되며, 질문 데이터는 배열로 관리
3. 답안 입력 및 제출
   사용자가 답안을 입력(answerField) 후 "Submit" 버튼을 클릭해 제출
   정답 여부는 answers 배열과 비교
4. 피드백 제공:
   정답이면 "Correct!", 오답이면 "Incorrect." 메시지가 오른쪽 feedbackArea에 추가
   정답 시 점수 20점씩 증가
5. 최종 점수
   모든 질문이 끝나면 최종 점수를 questionArea에 표시하고 입력 및 제출을 비활성
실행결과:
![1](https://github.com/user-attachments/assets/d80f8831-8935-4596-a827-e6e2cb9d321f)
![2](https://github.com/user-attachments/assets/a8542dec-bec6-4d99-b853-b808cd4fb731)
![3](https://github.com/user-attachments/assets/eee4b995-023a-448e-86d8-65ad299e2d69)
![4](https://github.com/user-attachments/assets/cc9ad1e0-84fd-4e64-8e63-de4b05d54ace)
![5](https://github.com/user-attachments/assets/4bb5e710-419a-4b84-a14b-a3be63417db1)
