package org.example.onlineauctionsystem.Service;

import org.example.onlineauctionsystem.Entity.WinnerUsers;
import org.example.onlineauctionsystem.Repository.WinnerUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerUsersService {
    @Autowired
    private WinnerUsersRepository winnerUsersRepository;

    public void saveWinnerUser(WinnerUsers winnerUser) {
        winnerUsersRepository.save(winnerUser);
    }

}
