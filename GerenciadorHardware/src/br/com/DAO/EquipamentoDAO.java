package br.com.DAO;


import br.com.DTO.EquipamentoDTO;
import br.com.DTO.UsuarioDTO;
import br.com.VIEWS.CadastroMaquinas;
import br.com.VIEWS.TelaLogin;
import br.com.VIEWS.TelaPrincipal;
import br.com.VIEWS.TelaUsuarios;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class EquipamentoDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
    
     public void limpar(){
    
         CadastroMaquinas.txtId.setText(null);
         CadastroMaquinas.txtIdLaboatorio.setText(null);
         CadastroMaquinas.txtRAM.setText(null);
         CadastroMaquinas.txtProcessador.setText(null);
         CadastroMaquinas.txtData.setText(null);
         CadastroMaquinas.txtNome.setText(null);
         CadastroMaquinas.txtArmazenamento.setText(null);
         CadastroMaquinas.txtNSerie.setText(null);
     

}
     public void editar (EquipamentoDTO dto){
         String sql = "update maquinas set id_laboratorio = ?, nome = ?, processador = ?, memoria_RAM = ?, armazenamento = ?, numero_serie = ?, data_aquisicao = ? where id = ?";
         conexao = ConexaoDAO.connector();
         
         try {
             pst = conexao.prepareStatement(sql);
             
             pst.setInt(1, dto.getId_laboratorio());
             pst.setString(2, dto.getNome());
             pst.setString(3, dto.getProcessador());
             pst.setString(4, dto.getRam());
             pst.setString(5, dto.getArmazenamento());
             pst.setString(6, dto.getNumero_serie());
             pst.setString(7, dto.getData_aquisicao());
             pst.setInt(8, dto.getId());
             
             int go = pst.executeUpdate();
             
             if (go > 0){
                 JOptionPane.showMessageDialog(null, "editado com sucesso");
             }
             
         } catch (Exception e) {
         }
     
     }
    public void criar(EquipamentoDTO dto) {
        String sql = "insert into maquinas (id, id_laboratorio, nome, processador, memoria_RAM, armazenamento, numero_serie,data_aquisicao) value(?, ?, ?, ?, ?, ?, ?, ?) ";
        conexao = ConexaoDAO.connector();

        try {
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, dto.getId());
            pst.setInt(2, dto.getId_laboratorio());
            pst.setString(3, dto.getNome());
            pst.setString(4, dto.getProcessador());
            pst.setString(5, dto.getRam());
            pst.setString(6, dto.getArmazenamento());
            pst.setString(7, dto.getNumero_serie());
            pst.setString(8, dto.getData_aquisicao());

            int go = pst.executeUpdate();

            if (go > 0) {
                JOptionPane.showMessageDialog(null, "adicionado com sucesso");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("maquinas.PRIMARY")) {
                JOptionPane.showMessageDialog(null, "id ja em uso");
            } else if(e.getMessage().contains("maquinas.PRIMARY")) {

            } else {
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
    }

    public void search(EquipamentoDTO dto) {
        String sql = "select * from maquinas where id = ?";
        conexao = ConexaoDAO.connector();

        try {
            pst = conexao.prepareStatement(sql);

            pst.setInt(1, dto.getId());
            rs = pst.executeQuery();

            if (rs.next()) {
                CadastroMaquinas.txtIdLaboatorio.setText(rs.getString(2));
                CadastroMaquinas.txtNome.setText(rs.getString(3));
                CadastroMaquinas.txtProcessador.setText(rs.getString(4));
                CadastroMaquinas.txtRAM.setText(rs.getString(5));
                CadastroMaquinas.txtArmazenamento.setText(rs.getString(6));
                CadastroMaquinas.txtNSerie.setText(rs.getString(7));
                CadastroMaquinas.txtData.setText(rs.getString(8));
               
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

    public void update(UsuarioDTO dto) {

        int res = JOptionPane.showConfirmDialog(null, "tem certeza que quer atualizar o usuario?",
                null, JOptionPane.YES_NO_OPTION);

        if (res == JOptionPane.YES_OPTION) {

            String sql = "update tb_usuarios set nome = ?, email = ?, nome_usuario = ?, senha = ? where id = ?";
            conexao = ConexaoDAO.connector();

            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, dto.getNomeUsuario());
                pst.setString(2, dto.getEmailUsuario());
                pst.setString(3, dto.getNomeUsuUsuario());
                pst.setString(4, dto.getSenhaUsuario());
                pst.setInt(5, dto.getIdUsuario());

                int go = pst.executeUpdate();

                if (go > 0) {
                    limpar();
                    TelaUsuarios.txtId.setText(null);
                    JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public void deletar(EquipamentoDTO dto) {

        int res = JOptionPane.showConfirmDialog(null, "tem certeza que quer deletar?",
                null, JOptionPane.YES_NO_OPTION);

        if (res == JOptionPane.YES_OPTION) {
            String sql = "delete from maquinas where id = ?";

            conexao = ConexaoDAO.connector();

            try {
                pst = conexao.prepareStatement(sql);

                pst.setInt(1, dto.getId());

                int go = pst.executeUpdate();

                if (go > 0) {
                    limpar();
                    JOptionPane.showMessageDialog(null, "deletado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "erro ao deletar");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
          
}
    }

