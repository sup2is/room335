package me.sup2is.room335.redis.domain.room;

import org.springframework.data.repository.CrudRepository;

public interface RoomRedisRepository extends CrudRepository<RoomCache, Long> {
}
