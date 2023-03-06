package mc.jun.skinshop;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Category;
import mc.jun.skinshop.domain.repository.CategoryRepository;
import mc.jun.skinshop.domain.repository.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class SkinShopApplication {

	private final CategoryRepository categoryRepository;
	private final MemberRepository memberRepository;
	private final String[] cateList = {"무서운", "몽환"};

	@PostConstruct
	public void init () {
		Arrays.stream(cateList).forEach(item -> {
			if (!categoryRepository.findByCateName(item).isPresent()) {
				categoryRepository.save(new Category(item));
			}
		});
		initMember("조영준");
	}

	private void initMember (String name) {
		memberRepository.save(new Member(name));
	}

	public static void main(String[] args) {
		SpringApplication.run(SkinShopApplication.class, args);
	}
}
