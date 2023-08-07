package utils;

import java.io.File;

public class GetFiles {
	
	    public static String caminho(String arquivo) {
//	        String caminhoRelativo = obterCaminhoRelativo("resource");
//	        
//	        String caminhoCompleto = caminhoRelativo + File.separator + "LoginScreen.fxml"; 
	    	String caminho = null;
			try {
				caminho = GetFiles.class.getResource("./").toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        System.out.println("O caminho arquivo é: " + caminho);
	        
	        return caminho;
	        
	    }

	    public static String obterCaminhoRelativo(String nomePasta) {
	        String caminhoAbsoluto = new File("").getAbsolutePath();
	        String separador = File.separator;

	        int indiceUltimoSeparador = caminhoAbsoluto.lastIndexOf(separador);

	        String caminhoSemArquivo = caminhoAbsoluto.substring(0, indiceUltimoSeparador);

	        String caminhoRelativo = caminhoSemArquivo + separador + nomePasta;
	        
	        System.out.println("o caminho relativo para o arquivo é: " + caminhoRelativo);
	        
			return caminhoRelativo;

	   }

}
