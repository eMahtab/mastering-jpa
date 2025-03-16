package net.mahtabalam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import net.mahtabalam.domain.Comment;
import net.mahtabalam.domain.Post;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("domainPU");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Post post1 = new Post();
            post1.setTitle("Example Post Title")
                 .setContent("Post Line 1\n Post Line 2\n Post Line 3")
                 .setCreatedAt(OffsetDateTime.now(ZoneOffset.of("+08:00")));


            Comment comment1 = new Comment();
            comment1.setComment("Some Example comment 1")
                    .setCreatedAt(OffsetDateTime.now(ZoneOffset.of("+08:00")));


            Comment comment2 = new Comment();
            comment2.setComment("Some Example comment 2")
                    .setCreatedAt(OffsetDateTime.now(ZoneOffset.of("+08:00")));

            post1.addComment(comment1);
            post1.addComment(comment2);

            em.persist(comment1);
            em.persist(comment2);
            em.persist(post1);

            em.getTransaction().commit();

            em.clear();
            Post fetchedPost = em.find(Post.class, post1.getId());
            System.out.println("Fetched Post :" + fetchedPost.getId() +"\n" +
                                fetchedPost.getTitle()+"\n"+
                                fetchedPost.getContent());

            em.clear();
            Comment fetchedComment = em.find(Comment.class, comment1.getId());
            System.out.println("Fetched Comment : " + fetchedComment.getId()+"\n"+
                                fetchedComment.getComment());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}