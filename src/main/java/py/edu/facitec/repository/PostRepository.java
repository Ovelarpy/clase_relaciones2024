package py.edu.facitec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import py.edu.facitec.model.Post;
                                                        //Entidad   //Tipo de tado deel pk
public interface PostRepository extends JpaRepository<Post, Long>{

}
