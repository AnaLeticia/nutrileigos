package br.com.ufg.si.nutrileigos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IMCServlet
 */
@WebServlet("/CalculoIMC")
public class IMCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IMCServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtem o peso do parametro da requisicao (la do formulario)
		String peso = request.getParameter("peso");
		
		// Faco o mesmo com a altura...tambem do parametro da requisicao (la do formulario)
		String altura = request.getParameter("altura");
		
		PrintWriter out = response.getWriter();
		if (peso != null) {
			// Injeto esse trecho de HTML na pagina, com o peso e a altura...
			out.println("<b>Sua massa &eacute; de " + peso + " kg e sua altura &eacute; de " + altura + " m.</b><br/></br>");
			
			Double imc = Double.parseDouble(peso) / Math.pow(Double.parseDouble(altura), 2);
			
			DecimalFormat df = new DecimalFormat("#.##");

			// StringBuilder: classe do Java para concatenar Strings (metodo append)
			StringBuilder sb = new StringBuilder();
			
			// Injeto esse trecho de HTML na pagina, com o IMC ja calculado...
			sb.append("<br> Seu IMC &eacute; de <h4>" + df.format(imc) + "</h4>");
			
			
			// if-else com os valores possiveis de IMC. Pra cada faixa de valor, eu gero uma mensagem diferente
			if (imc < 17) {
				// muito abaixo do peso
			} else if (imc >= 17 && imc <= 18.49) {
				// abaixo do peso
			} else if (imc >= 18.5 && imc <= 24.99) {
				sb.append("<div class=\"alert alert-success alert-dismissible\" role=\"alert\">");
				sb.append("<h3>Parab&eacute;ns!</h3><br> Voc&ecirc; est&aacute; com o <b>peso ideal</b> ;) <br>");
				sb.append(".</div>");
			} else if (imc >= 25 && imc <= 29.99) {
				// acima do peso
			} else if (imc >= 30 && imc <= 34.99) {
				// obesidade 1
			} else if (imc >= 35 && imc <= 39.99) {
				// obesidade 2 severa
			} else if (imc >= 40) {
				// obesidade 3 morbida
			}
			
			// Injeto o conteudo da minha StringBuilder com o alerta criado durante o if-else...
			out.println(sb.toString());
		}
	}

}
