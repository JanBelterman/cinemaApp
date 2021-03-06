package com.cinema.avans.cinemaapp.dataAccess.localRepositories;

import com.cinema.avans.cinemaapp.dataAccess.data.DatabaseManager;
import com.cinema.avans.cinemaapp.domain.SeatRowInstance;
import com.cinema.avans.cinemaapp.domain.Showing;
import com.cinema.avans.cinemaapp.domain.HallInstance;

/**
 * Created by JanBelterman on 28 March 2018
 */

public class LocalHallInstanceRepository {

    private DatabaseManager databaseManager;

    public LocalHallInstanceRepository(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }

    // When a hall instance is added to the database:
    // - The HallInstance itself has to be added
    // - All of the SeatRowInstances have to be added
    // - All of the SeatInstances per SeatRowInstance have to be added
    void createHallInstance(HallInstance hallInstance) {

        // Add HallInstance and add generated id
        hallInstance.setID(databaseManager.createHallInstance(hallInstance));

        // Add all SeatRow instances
        for (SeatRowInstance seatRowInstance : hallInstance.getSeatRowInstances()) {
            new LocalSeatRowInstanceRepository(databaseManager).createSeatRowInstance(seatRowInstance);

        }

    }

    public HallInstance getHallInstance(Showing showing) {

        // Get HallInstance
        HallInstance hallInstance = databaseManager.getHallInstance(showing.getHallInstance().getID());
        // Also get SeatRowInstances
        hallInstance.setSeatRowInstances(new LocalSeatRowInstanceRepository(databaseManager).getSeatRowInstances(hallInstance));

        // Return HallInstance
        return hallInstance;

    }

}
