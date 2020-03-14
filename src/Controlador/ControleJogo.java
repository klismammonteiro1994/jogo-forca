package Controlador;

import Modelo.ModeloJogo;
import static Modelo.ModeloJogo.ERRO_BANCO;
import Modelo.SementeMysql;
import Visual.Jogo;
import static Visual.Jogo.usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author klismam
 */
public class ControleJogo {

    private int erros = 0;
    private String palavraCorretaDoBanco = "";
    private char palavraDigitadaPeloUsuario;
    private String dica = "";
    private String texto = "";
    private String letrasDigitadas = "- ";
    private int pontuacao = 0;
    private int tentativas = 6;
    private int pontuacaoTotal = 0;
    private int count = 0;
    private String dificuldade = "";
    private char[] palavra;
    Jogo jogo = new Jogo();
    ModeloJogo mj = new ModeloJogo();

    public ControleJogo() {

        jogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jogo.setTitle("Jogo da Forca");
        jogo.setLocationRelativeTo(null);
        jogo.setResizable(false);
        jogo.setVisible(true);

        jogo.getBtnTentar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                metodoTentar();
            }
        });

        jogo.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                metodoVoltar();
            }
        });

    }

    public String getPalavraCorretaDoBanco() {
        return palavraCorretaDoBanco;
    }

    public void setPalavraCorretaDoBanco(String palavraCorretaDoBanco) {
        this.palavraCorretaDoBanco = palavraCorretaDoBanco;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    
    
    

//  metodo do que revela a letra pro usuario
    public void revelaLetra(String letra) {
        count = 0;
        boolean ganhou = false;
        palavraDigitadaPeloUsuario = letra.charAt(0);
        // verifica se a letra está na posição i da palavra , se tiver ela recebe 1
        for (int i = 0; i < palavraCorretaDoBanco.length(); i++) {
            if (palavraDigitadaPeloUsuario == palavraCorretaDoBanco.charAt(i)) {
                palavra[i] = 1;

            }
        }
        texto = "";
        // se a palavra na posição i tiver 0 recebe um traço senao recebe a letra correta do banco
        ganhou = true;
        for (int i = 0; i < palavraCorretaDoBanco.length(); i++) {
            if (palavraCorretaDoBanco.substring(i, i + 1).equals(" ")) { // logica que trata de palavras com espaço
                texto += "  ";
            } else {
                if (palavra[i] == 0) {
                    texto += " _ ";
                    ganhou = false;
                } else {
                    texto += " " + palavraCorretaDoBanco.charAt(i) + " ";
                    count = count + 1;
                }
            }
        }

        if (ganhou == true) {
            jogo.getJlPalavraEscondida().setText(texto);
            atualizaPontuacao();
            janelaVitoria();
        }

        jogo.getJlPalavraEscondida().setText(texto);

    }

    //metodo do botao tentar
    public void metodoTentar() {
        String letra = jogo.getTxtPalavra().getText().toLowerCase(); // pega e letra e converte pra minusculo

        if (letrasDigitadas.contains(letra)) { // condição para evitar a repetição de letras
            JOptionPane.showMessageDialog(null, "Voce ja digitou essa letra !!");
            jogo.getTxtPalavra().setText("");
        } else {
            letrasDigitadas = letrasDigitadas + " " + letra;
            if (palavraCorretaDoBanco.contains(letra)) {
                jogo.getTxtPalavra().setText("");
                jogo.getJlLetrasTentadas().setText(letrasDigitadas);
                revelaLetra(letra);
                atualizaPontuacao();
            } else {
                erros++;
                tentativas = tentativas - 1;
                String str2 = Integer.toString(tentativas);
                jogo.getJltentativasRestantes().setText(str2);
                jogo.getTxtPalavra().setText("");
                desenhaBoneco(erros);
                jogo.getJlLetrasTentadas().setText(letrasDigitadas);
                if (tentativas == 0) {
                    janelaDerrota();
                }
            }
        }

    }

    // metodo que atualiza a pontuação baseado na pontuação por letra
    public void atualizaPontuacao() {

        pontuacao = count * 10;
        String pontos = Integer.toString(pontuacao);
        jogo.getJlpontos().setText(pontos);

    }
// atualiza a pontuaçao no banco de dados de acordo com o modelo MVC
    public void atualizaPontuacaoBanco() {
  
        int ret = mj.upadatePontuacao(this);

        if (ret == ERRO_BANCO) {
            JOptionPane.showMessageDialog(null, "Erro No Banco !!");
        }
    }

    public void janelaVitoria() {

        JOptionPane.showMessageDialog(null, "Parabens !! Você Venceu !!");
        JOptionPane.showMessageDialog(null, "A Palavra Correta é " + palavraCorretaDoBanco + " !!");
        pontuacaoTotal = pontuacaoTotal + pontuacao;
        JOptionPane.showMessageDialog(null, "Sua Pontuação Nessa Rodada Foi " + pontuacao + " "
                + "Pontos ! \nPontuação Total De " + pontuacaoTotal + " Pontos !");
        pontuacao = 0;
        atualizaPontuacaoBanco();
        tentativas = 6;
        erros = 0;
        letrasDigitadas = "- ";
        jogo.getJlPalavraEscondida().setText("");
        jogo.getJlLetrasTentadas().setText("");
        texto = "";
        palavra();
    }

    public void janelaDerrota() {
        JOptionPane.showMessageDialog(null, "Você Perdeu !!");
        JOptionPane.showMessageDialog(null, "Sua Pontuação Final foi " + pontuacaoTotal + " Pontos !!");
        atualizaPontuacaoBanco();
        pontuacao = 0;
        tentativas = 6;
        erros = 0;
        jogo.getJlPalavraEscondida().setText("");
        jogo.getJlLetrasTentadas().setText("");
        texto = "";
        chamaTelaJogo();
    }

    // metodo que cria os traços no JLabel
    public void criaTracos() {

        for (int i = 0; i < palavraCorretaDoBanco.length(); i++) {
            if (palavraCorretaDoBanco.substring(i, i + 1).equals(" ")) {
                texto += "   ";
            } else {
                texto += " _ ";
            }

            jogo.getJlPalavraEscondida().setText(texto);
        }
    }

    //Metodo que desenha o boneco
    public void desenhaBoneco(int n) {

        jogo.getImgforca().setIcon(new javax.swing.ImageIcon(getClass().getResource("Dados/" + n + ".jpg")));

    }

    public void chamaTelaJogo() {

        jogo.dispose();
        ControleTelaJogo ctj = new ControleTelaJogo();
    }

    // metodo responsavel por buscar a palavra no banco 
    public void palavra() {

            mj.buscaPalavra(this);
            desenhaBoneco(0);
            jogo.getJlDificuldade().setText(dificuldade);
            System.out.println(palavraCorretaDoBanco);
            jogo.getJlDicas().setText(dica);
            jogo.getJlLetrasTentadas().setText(" ");
            String str2 = Integer.toString(tentativas);
            jogo.getJltentativasRestantes().setText(str2);
            String str = Integer.toString(pontuacao);
            jogo.getJlpontos().setText(str);
            jogo.getJlLetrasTentadas().setText("- ");
            String str3 = Integer.toString(pontuacaoTotal);
            jogo.getJlPontuacaoTotal().setText(str3);
            criaTracos();
            palavra = new char[palavraCorretaDoBanco.length()];// novo array de char com o mesmo tamanho da palavra do banco

    }

    public void metodoVoltar() {
        int opcao
                = JOptionPane.showOptionDialog(null,
                        "Realmente Deseja Sair ? (Seu Progresso Atual Será Perdido)",
                        "Pergunta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        new String[]{"Sim", "Não"}, "SIM");
        if (opcao == 0) {
            chamaTelaJogo();
        }

    }

    public static void main(String[] args) {

        ControleJogo cj = new ControleJogo();
        cj.palavra();

    }

}
