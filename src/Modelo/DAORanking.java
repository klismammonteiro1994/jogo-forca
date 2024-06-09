package Modelo;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class DAORanking extends AbstractTableModel{
    
    private ArrayList linhas = null;
    private String[] colunas = null;

    public DAORanking(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);    
    }
    
    public ArrayList getLinhas(){
        return linhas;
    }
    
    public void setLinhas(ArrayList dados){
        linhas = dados;
    }
    
    public String[] getColunas(){
        return colunas;
    }
    
    public void setColunas(String[] nomes){
        colunas = nomes;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getRowCount(){
        return linhas.size();
    }
    
    @Override
    public String getColumnName(int numCol) {
        return colunas[numCol];
    }
    
    /**
     *
     * @param numLin
     * @param numCol
     * @return
     */
    @Override
    public Object getValueAt(int numLin, int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
    
}
