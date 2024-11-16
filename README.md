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
