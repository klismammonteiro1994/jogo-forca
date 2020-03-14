package Modelo;

/**
 *
 * @author klismam
 */
public class Usuario {

    private int pontuacao;
    private int id_Usuario;
    private int MaiorPontuacao;
    private int Trofeu;
    private int MelhorPontuacao;
    private String nome_usuario;
    private String login;
    private String Senha;
    private String confirmaSenha;

    public Usuario() {

    }

    public Usuario(int pontuacao, int id_Usuario, int MaiorPontuacao, int Trofeu, int MelhorPontuacao, String nome_usuario, String login, String Senha) {
        this.pontuacao = pontuacao;
        this.id_Usuario = id_Usuario;
        this.MaiorPontuacao = MaiorPontuacao;
        this.Trofeu = Trofeu;
        this.MelhorPontuacao = MelhorPontuacao;
        this.nome_usuario = nome_usuario;
        this.login = login;
        this.Senha = Senha;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getMaiorPontuacao() {
        return MaiorPontuacao;
    }

    public void setMaiorPontuacao(int MaiorPontuacao) {
        this.MaiorPontuacao = MaiorPontuacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public int getTrofeu() {
        return Trofeu;
    }

    public void setTrofeu(int Trofeu) {
        this.Trofeu = Trofeu;
    }

    public int getMelhorPontuacao() {
        return MelhorPontuacao;
    }

    public void setMelhorPontuacao(int MelhorPontuacao) {
        this.MelhorPontuacao = MelhorPontuacao;
    }

}
