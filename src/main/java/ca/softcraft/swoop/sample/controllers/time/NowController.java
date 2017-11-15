package ca.softcraft.swoop.sample.controllers.time;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NowController {
	@GetMapping("/time/now")
	NowResult get() {
		return new NowResult(0, 0, 0, 0, 0, 0, 0);
	}
}
