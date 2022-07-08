package com.permissao.service;

import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class NativeQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void execute(String sql) {
        entityManager.createNativeQuery(sql).executeUpdate();
    }
}
