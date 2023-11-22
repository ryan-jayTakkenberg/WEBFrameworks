package app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractEntityRepositoryJpa <E extends  Identifiable>
        implements OffersRepository<E> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<E> theEntityClass;

    public AbstractEntityRepositoryJpa(Class<E> entityClass) {
        this.theEntityClass = entityClass;
    }

}
