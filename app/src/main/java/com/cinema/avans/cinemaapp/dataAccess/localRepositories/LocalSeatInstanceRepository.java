package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.SeatInstance;
import com.cinema.avans.cinemaapp.domain.SeatRowInstance;

import java.util.ArrayList;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class LocalSeatInstanceRepository {

    private DatabaseManager databaseManager;

    public LocalSeatInstanceRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;

    }

    void createSeatInstance(SeatInstance seatInstance) {

        // Create SeatInstance
        databaseManager.createSeatInstance(seatInstance);

    }

    ArrayList<SeatInstance> getSeatInstances(SeatRowInstance seatRowInstance) {

        // Get all SeatInstances within the SeatRowInstance given as parameter
        ArrayList<SeatInstance> seatInstances = databaseManager.getSeatInstances(seatRowInstance);
        for (SeatInstance seatInstance : seatInstances) {

            databaseManager.getSeatInstances(seatRowInstance);
            // Also add the SeatRowInstance
            seatInstance.setSeatRowInstance(seatRowInstance);

        }

        // Return SeatInstances
        return seatInstances;

    }

    public void updateSeats(ArrayList<SeatInstance> seatInstances) {

        for (SeatInstance seatInstance : seatInstances) {

            databaseManager.updateSeatInstance(seatInstance);

        }


    }

}
