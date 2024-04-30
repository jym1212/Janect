@Service
public class GPTService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "Key 값"; // 본인의 API 키를 사용하세요.
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public GPTService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

//DB에서 '' 가져오는 메소드
    public String getCertificateInfo(String id) {
        String url = "http://localhost:8080/CertificatesInfo/yearly/" + id;
       //
        ...
    }

//DB에서 '' 가져오는 메소드
    public String getCertificateRecord() {
        String url = "http://localhost:8080/CertificatesInfo/table";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

//DB에서 ''가져오는 메소드
    public String getHardLevel() {
        String url = "http://localhost:8080/CertificatesInfo/HardLevel";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

//질문하기 전에 GPT에 데이터 Input
    public String prepareMessage(String CertificateInfo, String CertificateRecord, String HardLevel) {
        return "Here is the Certificate info for the year: " + CertificateInfo + "\n"
                + "Here is the Certificate record: " + CertificateRecord + "\n"
                + "Here is the sunrise and sunset info for today: " + HardLevel + "\n";
    }

// String -> LocalTime으로 파싱
    private SunriseSunsetData parseSunriseSunset(String HardLevel) {
        // ...
    }

//질의응답 매뉴얼
    public String askGPT(String question) {
        try {
          // Data Input
            String CertificateInfo = getCertificateInfo("1");
            String CertificateRecord = getCertificateRecord();
            String HardLevel = getHardLevel();
            String preMessage = prepareMessage(CertificateInfo, foodRecord, sunriseSunset);

            String modifiedQuestion = question.toLowerCase();
            // 다음과 같은 질문을 포함한 경우의 답변 매뉴얼 생성
            if (modifiedQuestion.contains("시험 정보에 대해 알려줘")) {
                SunriseSunsetData sunriseSunsetData = parseSunriseSunset(sunriseSunset);
                if (sunriseSunsetData != null) {
                    LocalDateTime sunriseDateTime = sunriseSunsetData.getSunriseDateTime();
                    LocalDateTime sunsetDateTime = sunriseSunsetData.getSunsetDateTime();

                
                    preMessage += "시험의 주제는 이렇습니다. : " + formattedtextlogic1 + ", 난이도는: " + formattedtextlogic2 + "입니다.";
                } else {
                    // 오류 처리 로직 추가
                }
            } else if( // 조건) 
              // 답변에 포함시킬 내용
                // ...
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);

            List<Map<String, String>> messages = new ArrayList<>();

            // System message with preMessage
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", preMessage);
            messages.add(systemMessage);

            // User message with the question
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", question);
            messages.add(userMessage);
      
            // 답변 가능한 최대 글자수, 사용 모델 설정
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("messages", messages);
            requestBody.put("max_tokens", 500);
            requestBody.put("model", "gpt-3.5-turbo");

            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Response: " + response.getBody());
                Map<String, Object> responseBody = objectMapper.readValue(response.getBody(), Map.class);
                if (responseBody.containsKey("choices")) {
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                    if (choices != null && !choices.isEmpty() && choices.get(0).containsKey("message")) {
                        Map<String, Object> messageResponse = (Map<String, Object>) choices.get(0).get("message");
                        if (messageResponse.containsKey("content")) {
                            String answer = (String) messageResponse.get("content");
                            return answer.trim();
                        }
                    }
                }
            } else {
                throw new RuntimeException("ChatGPT API 요청 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("ChatGPT API 호출 중 오류 발생", e);
        }
        throw new RuntimeException("ChatGPT API 호출에서 예상치 못한 상황 발생");
    }
}