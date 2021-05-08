package com.full.project.tdd.TodoTdd.Repository;

import com.full.project.tdd.TodoTdd.Entity.ToDoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToDoRepositorySessionFactoryImpl implements ToDoRepositorySessionFactory {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<ToDoEntity> findAll() {

       return jdbcTemplate.query("select * from to_Do_Entity" , new BeanPropertyRowMapper<ToDoEntity>(ToDoEntity.class));
    }

  /*  @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
    @Override
    public List<ToDoEntity> findAll() {
        Session currentSession = getSession().getSessionFactory().getCurrentSession();

        Query<ToDoEntity> query = currentSession.createQuery("from ToDoEntity");

        List<ToDoEntity> todoLists = query.getResultList();
         return todoLists;
    }*/
}
