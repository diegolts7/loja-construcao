package controller;

import javax.swing.table.AbstractTableModel;

public class TablePersonalizada extends AbstractTableModel {
    private Object[][] data;
    private String[] columns;

    // Construtor
    public TablePersonalizada(Object[][] data, String[] columns) {
        this.data = data;
        this.columns = columns;
    }

    // Retorna o número de linhas da tabela
    @Override
    public int getRowCount() {
        return data.length;
    }

    // Retorna o número de colunas da tabela
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    // Retorna o valor de uma célula
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    // Define o valor de uma célula
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);  // Notifica que a célula foi atualizada
    }

    // Retorna o nome de uma coluna
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
