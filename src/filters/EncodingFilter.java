package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")                                         //サーブレットフィルターを宣言するために使用されるアノテーション
public class EncodingFilter implements Filter{  //インタフェースを実装するには、implements を用いて定義します
		//implements を用いた場合、インタフェース名で指定された抽象メソッドを実装することが義務付けられます

	public EncodingFilter() {                       //デフォルトコンストラクタ
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);         //リクエストとレスポンスのペアをフィルタ・チェーンとともに入力として取得する
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
}

