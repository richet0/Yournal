package com.yournal.yournal.Repositories;
import com.yournal.yournal.Models.Trade;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.time.LocalDate;



public interface TradesRepository extends CrudRepository <Trade, Long> {

    List<Trade> findByDateGreaterThanEqual(LocalDate date);
}
