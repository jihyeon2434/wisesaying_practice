import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Map<String, String> params = new HashMap<>();
	static List<WiseSaying> wiseSayings = new ArrayList<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int lastId = 0;
		List<WiseSaying> wiseSayings = new ArrayList<>();

		System.out.println("== 명언 앱 실행 ==");

		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("등록")) {
				int id = lastId + 1;
				System.out.print("명언: ");
				String content = sc.nextLine();
				System.out.print("작가: ");
				String author = sc.nextLine();
				System.out.printf("%d번 명언이 등록되었습니다.\n", id);
				WiseSaying wiseSaying = new WiseSaying(id, content, author);
				wiseSayings.add(wiseSaying);
				lastId++;

			} else if (cmd.equals("목록")) {
				System.out.println("번호  /  작가  / 명언  ");
				System.out.println("=".repeat(30));
				for (int i = wiseSayings.size() - 1; i >= 0; i--) {
					WiseSaying ws = wiseSayings.get(i);
					System.out.printf("%d / %s / %s \n", ws.getId(), ws.getAuthor(), ws.getContent());
				}

			} else if (cmd.equals("삭제")) {
				String[] cmdBits = cmd.split("\\?", 2);

				if (cmdBits.length == 1) {
					continue;
				}
				String[] paramBits = cmdBits[1].split("&");

				for (String paramStr : paramBits) {
					String[] paramStrBits = paramStr.split("=", 2);
					if (paramStrBits.length == 1) {
						continue;
					}
					String key = paramStrBits[0];
					String value = paramStrBits[1];
					params.put(key, value);

				}
				int id = getIntParam("id", -1);

				if (id == -1) {
					System.out.println("id(정수)를 제대로 입력해주세요");
					return;
				}
				// 입력된 id와 일치하는 명언 객체 찾기
				WiseSaying wiseSaying = findById(id);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					return;
				}

				// 찾은 명언 객체를 object기반으로 삭제
				wiseSayings.remove(wiseSaying);

				System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
			}
		}

	}

	private static WiseSaying findById(int id) {
			for (WiseSaying wiseSaying : wiseSayings) {
				if (wiseSaying.getId() == id) {
					return wiseSaying;
				}
			}

			return null;
	}

	public static String getParam(String name) {
		return params.get(name);
	}

	public static int getIntParam(String name, int defaultValue) {
		try {
			return Integer.parseInt(getParam(name));
		} catch (NumberFormatException e) {

		}
		return defaultValue;
	}

}

class WiseSaying {
	private int id;
	private String content;
	private String author;

	public WiseSaying(int id, String content, String author) {
		this.id = id;
		this.author = author;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}