@RestController
public class AliveController {

    @GetMapping("/")
    public String alive() {
        return "Backend is up!";
    }
}
