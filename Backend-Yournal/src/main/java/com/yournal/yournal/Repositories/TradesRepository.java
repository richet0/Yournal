package com.yournal.yournal.Repositories;
import com.yournal.yournal.Models.Trade;
import com.yournal.yournal.Models.User;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.time.LocalDate;




public interface TradesRepository extends CrudRepository <Trade, Long> {

    List<Trade> findByDateGreaterThanEqual(LocalDate date);
    List<Trade> findByUserAndDateAfter(User user, LocalDate date);
    List<Trade> findByUser(User user);
}
