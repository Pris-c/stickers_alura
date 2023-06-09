import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;



public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo, String texto) throws Exception{

        //Leitura de imagem local
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        
        //Leitura de imagem por URL
        /*InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BOGQzZTBjMjQtOTVmMS00NGE5LWEyYmMtOGQ1ZGZjNmRkYjFhXkEyXkFqcGdeQXVyMjUzOTY1NTc@.jpg")
        .openStream();*/

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //Cria imagem de transparencia com novo tamanho (em relação à imagem lida)
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //Copia imagem original para novaImagem (transparente) criada
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();            //"Caneta"
        graphics.drawImage(imagemOriginal, 0, 0, null);            //Escrever imagem antiga (poster) na nova (transparente)


        //Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);
        graphics.setColor(Color.MAGENTA);

        //Escrever uma frase na novaImagem
        String textoFigurinha = texto;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo =  fontMetrics.getStringBounds(textoFigurinha, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;

        graphics.drawString(textoFigurinha, posicaoTextoX, novaAltura - 100);

        //Desafio 1 - Aula 2: Criar diretório de saída se este ainda não existe
        if (!new File("saida").exists()) {
            new File("saida").mkdir();
        }
        //Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
        }


}
