package ca.mcgill._1.videogamessystem.repository;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Long>{
    public Reply findReplyById(Long id);
}
