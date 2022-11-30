package com.cross.Danmat.WordRelay.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.WordRelay.Command.PointCommand;
import com.cross.Danmat.WordRelay.Command.UsedCommand;
import com.cross.Danmat.WordRelay.Domain.Word;
import com.cross.Danmat.WordRelay.Service.WordRelayServiceImpl;

@Controller
@RequestMapping("wordRelay")
public class WordRelayController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	WordRelayServiceImpl wordRelayService = context.getBean("wordRelayService", WordRelayServiceImpl.class);
	String random = null;
	int count;
	@GetMapping
	public String wordRelayPage(Model model) {
		List<Word> randomWord = wordRelayService.randomWord();
		model.addAttribute("randomWord",randomWord.get(0).getWord());
		random = randomWord.get(0).getWord();
		System.out.println(random);
		wordRelayService.delete(); //사용된 단어 DB 데이터 삭제
		System.out.println("데이터삭제");
		wordRelayService.delete2();
		System.out.println("데이터삭제");
		if(count >= 0) { //컴퓨터의 오류로 재시작할때 점수초기화 방지를 위해
			count = 0;
		}else {
			wordRelayService.delete3();
			System.out.println("데이터삭제");
		}
		return "wordRelay/wordRelay";
	}
	
	
	@PostMapping
	public String wordRelayAjax(@ModelAttribute("word") Word word, Model model,HttpServletRequest request) {
		int result = 0; // 1 = 마지막글씨와 맞지 않을때  2 = DB에 없는단어  3 = 사용한 단어 4 = 승리
		List<PointCommand> point = wordRelayService.addPoint();
		model.addAttribute("point",point);
		model.addAttribute("point",point);
 		model.addAttribute("result",result);
		
		String input_text = request.getParameter("input_text");
		model.addAttribute("input_text",input_text);
		
		int usedWord = wordRelayService.usedWord(input_text);
		wordRelayService.isuse(input_text); //사용 단어 등록
		List<UsedCommand> used = wordRelayService.findUsed();
		model.addAttribute("used",used);
		
		int checkWord = wordRelayService.checkWord(input_text); //DB에 등록된 단어 확인
		
		System.out.println(random);
		if(random != null) { //처음 랜덤 word가 값이 있을때 아래 로직이 실행
			if(checkWord == 0) { //입력값이 DB에 등록이 된경우 로직 실행
				String[] inputSpl = input_text.split("");
				String[] randomSpl = random.split("");
				
				random = null;
				if(inputSpl[0].contentEquals(randomSpl[randomSpl.length -1 ])) { //랜덤 Word의 마지막글자와 입력값의 첫번재 글자가 맞는지 확인
					String lastWord = inputSpl[inputSpl.length - 1]; //입력값 마지막 글자
					List<Word> answer = wordRelayService.randomSplWord(lastWord+"%"); //입력값의 마지막 글자로 다음에 나올단어 추출
					model.addAttribute("answer",answer);
					wordRelayService.answer(answer.get(0).getWord());  //answer 저장
					wordRelayService.point(10);
					point = wordRelayService.addPoint();	
					model.addAttribute("point",point);
				}else if(!inputSpl[0].contentEquals(randomSpl[randomSpl.length -1 ])) {//랜덤 Word의 마지막글자와 입력값의 첫번재 글자가 맞는지 않을때
					result = 1; //끝말이 맞지않아
					System.out.println("첫번째 끝말");
					model.addAttribute("result",result);
					wordRelayService.delete(); //사용된 단어 DB 데이터 삭제
					wordRelayService.delete2(); // 저장된 answer 데이터 삭제
					wordRelayService.delete3();
				}else if(checkWord == 1) { //DB에 등록된 단어가 아닐때 
						result = 2; //없는단어
						System.out.println("두번째 없는단어");
						model.addAttribute("result",result);
						wordRelayService.delete();
						wordRelayService.delete2();
						wordRelayService.delete3();
						System.out.println("dd");
				}else 	if(usedWord == 1) { //이미 사용한 단어
					result = 3; //사용한 단어
					System.out.println("세번째 사용단어");
					model.addAttribute("result",result);
					wordRelayService.delete();
					wordRelayService.delete2();
					wordRelayService.delete3();
				}
			}
		}else if (random == null) { //random word가 없을때 아래 로직 실행
			String answer = wordRelayService.findAnswer().get(wordRelayService.findAnswer().size()-1).getAnswerWord();
			String[] answerSpl = answer.split("");
			String[] inputSpl = input_text.split(""); //입력값을 자른후 배열에
			wordRelayService.delete2();
			if(answerSpl[answerSpl.length -1].contentEquals(inputSpl[0])) {
				String lastWord = inputSpl[inputSpl.length - 1]; //입력값의 마지막 글씨
				List<Word> nextanswer = wordRelayService.randomSplWord(lastWord+"%");//입력값의 마지막글씨로 랜덤글자를 추출
				if(wordRelayService.randomSplWord(lastWord+"%").size() == 0) {
					result = 4; //승리
					System.out.println("네번째 컴퓨터 단어X");
					model.addAttribute("result",result);
					wordRelayService.delete(); //사용된 단어 DB 데이터 삭제
					wordRelayService.delete2();// 저장된 answer 데이터 삭제
					count ++;
				}else if(wordRelayService.randomSplWord(lastWord+"%").size() != 0){
					wordRelayService.answer(nextanswer.get(0).getWord()); //answer 저장
					model.addAttribute("answer",nextanswer);
					wordRelayService.point(10);
					point = wordRelayService.addPoint();	
					model.addAttribute("point",point);
				}else if(checkWord == 1) { //DB에 등록된 단어가 아닐때 
					System.out.println("다섯번째 없는단어");
					result = 2; //없는단어
					model.addAttribute("result",result);
					wordRelayService.delete();
					wordRelayService.delete2();
					wordRelayService.delete3();
					System.out.println("dd");
			}else if(usedWord == 1) { //이미 사용한 단어
				result = 3; //사용한 단어
				System.out.println("여섯번째 사용한단어");
				model.addAttribute("result",result);
				wordRelayService.delete();
				wordRelayService.delete2();
				wordRelayService.delete3();
			}
			}else if(!answerSpl[answerSpl.length -1].contentEquals(inputSpl[0])){
				result = 1; //끝말이 맞지않아
				System.out.println("일곱번째 끝말");
				model.addAttribute("result",result);
				wordRelayService.delete(); //사용된 단어 DB 데이터 삭제
				wordRelayService.delete2();// 저장된 answer 데이터 삭제
				wordRelayService.delete3();
			}
		}
		if(checkWord == 1) { //DB에 등록된 단어가 아닐때 
			result = 2; //없는단어
			System.out.println("여덟번째 없는단어"); //count -
			model.addAttribute("result",result);
			wordRelayService.delete();
			wordRelayService.delete2();
			wordRelayService.delete3();
	    } if(usedWord == 1) { //이미 사용한 단어
	    	System.out.println("아홉번째 사용단어");
		result = 3; //사용한 단어
		model.addAttribute("result",result);
		wordRelayService.delete();
		wordRelayService.delete2();
		wordRelayService.delete3();
	}
		

		return "wordRelay/ajax";
	}
}
