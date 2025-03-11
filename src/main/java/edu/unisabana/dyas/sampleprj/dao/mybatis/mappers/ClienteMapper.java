package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

/**
 *
 * @author cesarvefe
 */
public interface ClienteMapper {
    
    /**
     * Consultar un cliente por su ID
     * @param id Identificador del cliente
     * @return Cliente con la información consultada
     */
    public Cliente consultarCliente(@Param("idcli") int id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id Cliente que renta el item
     * @param idit ID del item rentado
     * @param fechainicio Fecha de inicio de la renta
     * @param fechafin Fecha de finalización de la renta
     */

    public void agregarItemRentadoACliente(
            @Param("id") int id, 
            @Param("idit") int idit, 
            @Param("fechainicio") Date fechainicio,
            @Param("fechafin") Date fechafin);
            

    /**
     * Consultar todos los clientes con sus ítems rentados
     * @return Lista de clientes con sus respectivos ítems rentados
     */
    public List<Cliente> consultarClientes();
}
