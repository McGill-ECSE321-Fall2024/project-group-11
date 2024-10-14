package ca.mcgill._1.videogamessystem.repository;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill._1.videogamessystem.model.Reply;

public interface ReplyRepository extends CrudRepository<Reply, Date>{
    public Reply findReply(String content, Date replyDate);
}
