<script>
    // 질의문 버튼 클릭 이벤트 핸들러
    document.getElementById('question-button').addEventListener('click', async () => {
      const questionInput = document.getElementById('question-input');
      const question = questionInput.value.trim();
      questionInput.value = '';

      if (question) {
        const response = await fetch('http://localhost:8080/gpt/chat/completions', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            question: question
          })
        });

        if (response.ok) {
          const answer = await response.text();
          const answerContainer = document.getElementById('answer-container');
          answerContainer.innerHTML = answer;
        } else {
          console.error('Failed to fetch GPT answer');
        }
      }
    });
</script>