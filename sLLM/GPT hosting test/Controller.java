@CrossOrigin
@RestController
@RequestMapping("/gpt")
public class GPTController {
	// Service의 class file 명 : GPTService
    private final GPTService gptService;

    @Autowired
    public GPTController(GPTService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/chat/completions")
    public String askGPT(@RequestBody String question) {
        return gptService.askGPT(question);
    }
}
