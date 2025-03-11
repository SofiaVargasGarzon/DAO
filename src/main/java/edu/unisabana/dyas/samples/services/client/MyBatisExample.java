/*
 * Copyright (C) 2015 cesarvefe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.unisabana.dyas.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Item;

/**
 *
 * @author cesarvefe
 */

public class MyBatisExample {

    private static SqlSessionFactory sqlSessionFactory;

    private static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }
    public static void main(String[] args) {
        try (SqlSession sqlSession = MyBatisExample.getSqlSessionFactory().openSession()) {
            ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
            
            // Insertar un item
            Item nuevoItem = new Item(null, 5, "Cámara", "Cámara profesional", null, 1500, null, null);
            mapper.insertarItem(nuevoItem);
            sqlSession.commit();
            System.out.println("Item insertado.");

            // Consultar un item específico
            Item item = mapper.consultarItem(5);
            System.out.println("Item consultado: " + item.getNombre());

            // Consultar todos los items
            List<Item> items = mapper.consultarItems();
            System.out.println("Items disponibles: " + items.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
