package com.example.javahibernateorderservice.repository.impl;

import com.example.javahibernateorderservice.model.Order;
import com.example.javahibernateorderservice.repository.OrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> findAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                List<Order> orders = session.createSelectionQuery("FROM Order", Order.class).getResultList();

                transaction.commit();
                return orders;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Failed to find all orders", e);
            }
        }
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Order order = session.find(Order.class, orderId);

                transaction.commit();
                return Optional.ofNullable(order);
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Failed to find order by id: " + orderId, e);
            }
        }
    }

    @Override
    public Order createOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                session.persist(order);

                transaction.commit();
                return order;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Failed to save order", e);
            }
        }
    }

    @Override
    public Order updateOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Order managedOrder = session.merge(order);

                transaction.commit();
                return managedOrder;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Failed to update order", e);
            }
        }
    }

    @Override
    public void deleteOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                if (!session.contains(order)) {
                    order = session.merge(order);
                }
                session.remove(order);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw new RuntimeException("Failed to delete order", e);
            }
        }
    }
}
