package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.unisabana.dyas.samples.entities.Cliente;

public class MyBatisTest {

    public static void main(String[] args) {
        try {
            // 1. Cargar la configuración de MyBatis
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 2. Crear una sesión SQL
            try (SqlSession session = sqlSessionFactory.openSession()) {
                ClienteMapper clienteMapper = session.getMapper(ClienteMapper.class);

                // 3. Ejecutar la consulta
                List<Cliente> clientes = clienteMapper.consultarClientes();

                // 4. Imprimir los resultados
                for (Cliente cliente : clientes) {
                    System.out.println("Cliente: " + cliente.getNombre() + ", Documento: " + cliente.getDocumento());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
