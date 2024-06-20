CREATE OR REPLACE PROCEDURE clearAll
AS
    BEGIN
        EXECUTE IMMEDIATE 'TRUNCATE TABLE BICYCLE';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE INVOICE';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE TRIP';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE PARK';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE PATHS';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE POIS';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE REGISTERED_USERS';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE SCOOTER';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE VEHICLE';
        EXECUTE IMMEDIATE 'TRUNCATE TABLE LOCATIONS';
END;
/

------------------------------------------------------------------USERS----------------------------------------------------

----Add a user

CREATE OR REPLACE PROCEDURE addRegisteredUser(g_username VARCHAR, g_email VARCHAR, g_gender VARCHAR, g_height INTEGER, g_weight INTEGER, g_cyclingAverageSpeed NUMBER, g_pwd VARCHAR, g_visa INTEGER, g_points INTEGER)
AS
    BEGIN
        INSERT INTO registered_users VALUES(g_username,g_email, g_gender, g_height, g_weight, g_cyclingAverageSpeed, g_visa, g_pwd, g_points);
END;
/

-----Remove a user

CREATE OR REPLACE PROCEDURE removeRegisteredUser(g_username VARCHAR)
AS
    BEGIN
    DELETE FROM registered_users WHERE username = g_username;
    IF SQL % NOTFOUND
        THEN RAISE_APPLICATION_ERROR(-20001,'ERROR,USERNAME NOT FOUND');
    END IF;
END;
/

----Update a user

CREATE OR REPLACE PROCEDURE updateUser(g_username VARCHAR, g_email VARCHAR, g_gender VARCHAR, g_height INTEGER, g_weight INTEGER, g_speed NUMBER, g_visa INTEGER, g_pwd VARCHAR, g_points INTEGER)
IS
    BEGIN
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.EMAIL = g_email
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.GENDER = g_gender
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.HEIGHT = g_height
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.WEIGHT = g_weight
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.CYCLINGAVERAGESPEED = g_speed
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.VISA = g_visa
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.PWD = g_pwd
        WHERE USERNAME = g_username;
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.POINTS = g_points
        WHERE USERNAME = g_username;
END;
/

----Gets a user

CREATE OR REPLACE FUNCTION getUser(g_username VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_user SYS_REFCURSOR;
    BEGIN
    OPEN cur_user FOR SELECT * FROM REGISTERED_USERS
        WHERE REGISTERED_USERS.username = g_username;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20002, 'ERROR, USER NOT FOUND');
        END IF;
    RETURN cur_user;
END;
/

----Get Usage history

CREATE OR REPLACE FUNCTION getHistory(g_username VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_hist SYS_REFCURSOR;
    BEGIN
    OPEN cur_hist FOR
        SELECT * FROM TRIP
        WHERE TRIP.USERNAME = g_username AND ENDING_PARK IS NOT NULL AND END_TIME IS NOT NULL;
    RETURN cur_hist;
END;
/

------------------------------------------------------------------Parks----------------------------------------------------

----Add a park

CREATE OR REPLACE PROCEDURE addPark(g_park_id VARCHAR, g_park_description VARCHAR, g_lat_park NUMBER, g_long_park NUMBER, g_bike_capacity INTEGER, g_scooter_capacity INTEGER, g_elevation INTEGER, g_park_input_voltage INTEGER, g_park_input_current INTEGER)
AS
    BEGIN
    INSERT INTO LOCATIONS VALUES(g_park_id, g_park_description,g_lat_park,g_long_park,g_elevation);
    INSERT INTO PARK
    VALUES (g_park_id, g_park_description, g_lat_park, g_long_park, g_bike_capacity , g_scooter_capacity, g_elevation, g_park_input_voltage, g_park_input_current);
END;
/

----Remove a park

CREATE OR REPLACE PROCEDURE removePark(g_park_id VARCHAR)
AS
    BEGIN
        DELETE FROM PARK WHERE PARK.PARK_ID = g_park_id;
        IF SQL % NOTFOUND
            THEN RAISE_APPLICATION_ERROR (-20003, 'ERROR, PARK NOT FOUND');
        END IF;
END;
/

----Update a Park

CREATE OR REPLACE PROCEDURE updatePark(g_park_id VARCHAR, g_park_desciption VARCHAR, g_lat NUMBER, g_long NUMBER, g_bike INTEGER, g_scooter INTEGER, g_elevation INTEGER, g_voltage INTEGER, g_current INTEGER)
IS
    BEGIN
        UPDATE PARK SET PARK.PARK_DESCRIPTION = g_park_desciption
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.LAT_PARK = g_lat
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.LONG_PARK = g_long
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.BIKE_CAPACITY = g_bike
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.SCOOTER_CAPACITY = g_scooter
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.ELEVATION = g_elevation
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.PARK_INPUT_VOLTAGE = g_voltage
        WHERE PARK.PARK_ID LIKE g_park_id;
        UPDATE PARK SET PARK.PARK_INPUT_CURRENT = g_current
        WHERE PARK.PARK_ID LIKE g_park_id;
END;
/

----Get a Park

CREATE OR REPLACE FUNCTION getPark(g_park_id VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_park SYS_REFCURSOR;
    BEGIN
    OPEN cur_park FOR SELECT * FROM PARK, LOCATIONS
        WHERE LOCATIONS.ID_LOCATION = g_park_id AND PARK.PARK_ID=LOCATIONS.ID_LOCATION;
    RETURN cur_park;
END;
/

------Get all Parks

CREATE OR REPLACE FUNCTION getAllParks RETURN SYS_REFCURSOR
AS
    curAllParks SYS_REFCURSOR;
    BEGIN
        OPEN curAllParks FOR
        SELECT l.id_location, l.location_description, l.lat, l.longi,  l.elevation, pa.bike_capacity, pa.scooter_capacity, pa.park_input_voltage,pa.park_input_current
        FROM Locations l, park pa
        WHERE l.id_location = pa.park_id AND l.id_location IN ( SELECT park_id FROM park);
        RETURN curAllParks;
END;
/

------------------------------------------------------------------POIS----------------------------------------------------

----Add Point of interest

CREATE OR REPLACE PROCEDURE addPoi(g_point_id VARCHAR,g_lat_point NUMBER,g_long_point NUMBER,g_elevation INTEGER,g_point_description VARCHAR)
AS
    BEGIN
        INSERT INTO LOCATIONS VALUES(g_point_id, g_point_description,g_lat_point,g_long_point,g_elevation);
        INSERT INTO POIS VALUES(g_point_id,g_lat_point,g_long_point ,g_elevation,g_point_description);
END;
/

----Remove Point of interest

CREATE OR REPLACE PROCEDURE removePoi(g_point_id VARCHAR)
AS
    BEGIN
        DELETE FROM pois WHERE point_id = g_point_id;
		DELETE FROM locations WHERE id_location = g_point_id;
        IF SQL%NOTFOUND
            THEN RAISE_APPLICATION_ERROR(-20005,'ERROR, POINT NOT FOUND');
        END IF;
END;
/

----Update a Point of Interest

CREATE OR REPLACE PROCEDURE updatePoi(g_id VARCHAR, g_lat NUMBER, g_long NUMBER, g_elevation INTEGER, g_description VARCHAR)
IS
    BEGIN
        UPDATE POIS SET POIS.LAT_POI = g_lat
        WHERE POIS.POINT_ID = g_id;
        UPDATE POIS SET POIS.LONG_POI = g_long
        WHERE POIS.POINT_ID = g_id;
        UPDATE POIS SET POIS.ELEVATION = g_elevation
        WHERE POIS.POINT_ID = g_id;
        UPDATE POIS SET POIS.POINT_DESCRIPTION = g_description
        WHERE POIS.POINT_ID = g_id;
END;
/
----Get a point of interest

CREATE OR REPLACE FUNCTION getPoi(id_poi VARCHAR)
RETURN SYS_REFCURSOR
AS
    cur_location SYS_REFCURSOR;
    BEGIN
        OPEN cur_location FOR SELECT * FROM POIS, LOCATIONS
        WHERE LOCATIONS.ID_LOCATION = id_poi AND POIS.POINT_ID = LOCATIONS.ID_LOCATION;
    RETURN cur_location;
END;
/

----Get all poitns of interest

CREATE OR REPLACE FUNCTION getAllPois RETURN SYS_REFCURSOR
AS
    curAllInterestPoints SYS_REFCURSOR;
    BEGIN
        OPEN curAllInterestPoints
        FOR
        SELECT * FROM locations l
        WHERE l.id_location IN ( SELECT point_id FROM POIS);
        RETURN curAllInterestPoints;
END;
/
------------------------------------------------------------------VEHICLE----------------------------------------------------

----Add a bike

CREATE OR REPLACE PROCEDURE addBicycle(g_vehicle_id VARCHAR , latA NUMBER, longA NUMBER, g_vehicle_status VARCHAR, g_weight INTEGER, g_aerodynamic_coef NUMBER, g_frontal_area NUMBER, g_wheel_size INTEGER)
AS
    idPark VARCHAR(100);
    BEGIN
        SELECT id_location  INTO idPark FROM LOCATIONS
            WHERE LOCATIONS.LAT = latA AND LOCATIONS.LONGI = longA;
        INSERT INTO vehicle VALUES(g_vehicle_id, idPark, g_vehicle_status, g_weight, g_aerodynamic_coef, g_frontal_area);
        INSERT INTO bicycle VALUES(g_vehicle_id, g_wheel_size);
END;
/

----Remove a bike

CREATE OR REPLACE PROCEDURE removeBicycle(g_vehicle_id VARCHAR)
AS
    BEGIN
    DELETE FROM bicycle WHERE vehicle_id = g_vehicle_id;
    IF SQL%NOTFOUND
        THEN RAISE_APPLICATION_ERROR(-20006,'ERROR, BICYCLE NOT FOUND');
    END IF;
END;
/

----Update a Bike

CREATE OR REPLACE PROCEDURE updateBike(g_id VARCHAR, g_latPark NUMBER, g_longPark NUMBER, g_status VARCHAR, g_weight INTEGER,
                                        g_aerodynamic_coef NUMBER, g_frontal_area NUMBER, g_wheel_size INTEGER)
IS
   new_park_id             VARCHAR(100);
BEGIN
        SELECT id_location  INTO new_park_id FROM LOCATIONS
            WHERE LOCATIONS.LAT = g_latPark AND LOCATIONS.LONGI = g_longPark;

            UPDATE VEHICLE SET VEHICLE.PARK_ID = new_park_id
            WHERE VEHICLE.VEHICLE_ID LIKE g_id;

            UPDATE VEHICLE SET VEHICLE.VEHICLE_STATUS = g_status
            WHERE VEHICLE.VEHICLE_ID like g_id;

            UPDATE VEHICLE SET VEHICLE.weight = g_weight
            WHERE VEHICLE.VEHICLE_ID LIKE g_id;

            UPDATE VEHICLE SET VEHICLE.aerodynamic_coef =g_aerodynamic_coef
            WHERE VEHICLE.VEHICLE_ID LIKE g_id;

            UPDATE VEHICLE SET VEHICLE.FRONTAL_AREA = g_frontal_area
            WHERE VEHICLE.VEHICLE_ID LIKE g_id;

            UPDATE BICYCLE SET BICYCLE.WHEEL_SIZE = g_wheel_size
            WHERE BICYCLE.VEHICLE_ID  LIKE g_id;

            IF SQL%NOTFOUND
                THEN RAISE_APPLICATION_ERROR(-20020, 'ERROR, NO VEHICLE WITH THAT ID');
            END IF;
END;
/
----Get a bike

CREATE OR REPLACE FUNCTION getBike (p_bike_id VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_bike SYS_REFCURSOR;
        BEGIN
        OPEN cur_bike FOR 
        SELECT V.*,B.wheel_size FROM VEHICLE V, BICYCLE B
        WHERE B.VEHICLE_ID =p_bike_id AND V.VEHICLE_ID=p_bike_id;

        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20007, 'ERROR, BICYCLE DOES NOT EXIST');
        END IF;
    RETURN cur_bike;
END;
/

----Get all bikes

CREATE OR REPLACE FUNCTION getAllBikes RETURN SYS_REFCURSOR
AS
    cur_all_bikes SYS_REFCURSOR;
        BEGIN
        OPEN cur_all_bikes FOR 
        SELECT V.*,B.wheel_size 
        FROM VEHICLE V, BICYCLE B
        where V.vehicle_id=B.vehicle_id;
        
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20008, 'ERROR, NO BIKE FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_bikes;
END;
/

----Add a scooter

CREATE OR REPLACE PROCEDURE addScooter(g_vehicle_id VARCHAR ,g_lat_park NUMBER, g_long_park NUMBER, g_vehicle_status VARCHAR, g_weight INTEGER,
                                        g_aerodynamic_coef NUMBER, g_frontal_area NUMBER,  g_scooter_type VARCHAR, g_max_battery NUMBER, g_actual_battery NUMBER, g_motor INTEGER, g_loctime timestamp)
AS
    idPark varchar(100);
    BEGIN
        SELECT id_location  INTO idPark FROM LOCATIONS
            WHERE LOCATIONS.LAT = g_lat_park AND LOCATIONS.LONGI = g_long_park;
        INSERT INTO vehicle VALUES(g_vehicle_id,idPark, g_vehicle_status, g_weight, g_aerodynamic_coef, g_frontal_area);
        INSERT INTO scooter VALUES(g_vehicle_id, g_scooter_type, g_max_battery, g_actual_battery, g_motor,g_loctime);
END;
/

----Remove a scooter

CREATE OR REPLACE PROCEDURE removeScooter(g_vehicle_id VARCHAR)
AS
    BEGIN
        DELETE FROM scooter WHERE vehicle_id = g_vehicle_id;
        IF SQL % NOTFOUND
            THEN RAISE_APPLICATION_ERROR(-20009,'ERROR, SCOOTER NOT FOUND');
        END IF;
END;
/

----Update a scooter

CREATE OR REPLACE PROCEDURE updateScooter(g_id VARCHAR,g_scooter_type VARCHAR,g_latPark NUMBER,g_longPark NUMBER, g_status VARCHAR,g_max_battery NUMBER ,g_actual_battery NUMBER,g_weight INTEGER,g_aerodynamic_coef NUMBER,g_frontal_area NUMBER,g_motor INTEGER,g_loctime timestamp)
IS

   new_park_id             VARCHAR(100);

BEGIN
        SELECT id_location  INTO new_park_id
        FROM LOCATIONS
        WHERE LOCATIONS.LAT = g_latPark AND LOCATIONS.LONGI = g_longPark;



    UPDATE VEHICLE SET VEHICLE.park_id = new_park_id
    WHERE VEHICLE.VEHICLE_ID LIKE g_id;

    UPDATE VEHICLE SET VEHICLE.VEHICLE_STATUS = g_status
    WHERE VEHICLE.VEHICLE_ID like g_id;

     UPDATE VEHICLE SET VEHICLE.weight = g_weight
    WHERE VEHICLE.VEHICLE_ID LIKE g_id;

     UPDATE VEHICLE SET VEHICLE.aerodynamic_coef =g_aerodynamic_coef
    WHERE VEHICLE.VEHICLE_ID LIKE g_id;

     UPDATE VEHICLE SET VEHICLE.FRONTAL_AREA = g_frontal_area
    WHERE VEHICLE.VEHICLE_ID LIKE g_id;

    UPDATE SCOOTER SET SCOOTER.scooter_type = g_scooter_type
    WHERE SCOOTER.vehicle_id  LIKE g_id;

    UPDATE SCOOTER SET SCOOTER.max_battery = g_max_battery
    WHERE SCOOTER.vehicle_id  LIKE g_id;

     UPDATE SCOOTER SET SCOOTER.actual_battery = g_actual_battery
    WHERE SCOOTER.vehicle_id  LIKE g_id;

     UPDATE SCOOTER SET SCOOTER.motor =g_motor
     WHERE SCOOTER.vehicle_id  LIKE g_id;

   UPDATE SCOOTER SET SCOOTER.lock_time =g_loctime
     WHERE SCOOTER.vehicle_id  LIKE g_id;


    IF SQL%NOTFOUND THEN RAISE_APPLICATION_ERROR(-20020, 'ERROR, NO VEHICLE WITH THAT ID');
    END IF;
END;
/


----Gets a scooter

CREATE OR REPLACE FUNCTION getScooter (p_scooter_id VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_scooter SYS_REFCURSOR;
    BEGIN
        OPEN cur_scooter FOR SELECT V.*,S.SCOOTER_TYPE, S.MAX_BATTERY, S.ACTUAL_BATTERY,S.MOTOR,S.lock_time FROM VEHICLE V, SCOOTER S
        WHERE V.VEHICLE_ID =p_scooter_id AND S.VEHICLE_ID=p_scooter_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20010, 'ERROR, SCOOTER DOES NOT EXIST');
        END IF;
    RETURN cur_scooter;
END;
/

----Get all scooters

CREATE OR REPLACE FUNCTION getAllScooters RETURN SYS_REFCURSOR
AS
    cur_all_scooter SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_scooter FOR 
        SELECT V.*,S.SCOOTER_TYPE, S.MAX_BATTERY, S.ACTUAL_BATTERY,S.MOTOR,S.lock_time
        FROM VEHICLE V, SCOOTER S
        where V.vehicle_id=S.vehicle_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20011, 'ERROR, NO SCOOTER FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_scooter;
END;
/

----Get all bikes in park

CREATE OR REPLACE FUNCTION getAllBikeInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR AS
    cur_all_bike_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_bike_park FOR 
        SELECT V.vehicle_id, P.PARK_ID,v.vehicle_status,V.weight, v.aerodynamic_coef,v.frontal_area, B.wheel_size 
        FROM PARK P, VEHICLE V, BICYCLE B
        WHERE P.PARK_ID=p_park_id AND  V.PARK_ID=P.PARK_ID AND V.vehicle_id=B.vehicle_id;
        
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20012, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_bike_park;
END;
/

----Get all available bikes in park

CREATE OR REPLACE FUNCTION getAllAvailableBikeInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_all_available_bike_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_available_bike_park FOR 
        SELECT  V.vehicle_id,P.PARK_ID, v.vehicle_status ,V.weight,v.aerodynamic_coef,v.frontal_area, B.wheel_size
        FROM VEHICLE V, BICYCLE B, PARK P
        WHERE P.PARK_ID=p_park_id AND V.PARK_ID=P.PARK_ID AND V.VEHICLE_STATUS = UPPER('AVAILABLE') AND V.vehicle_id=B.vehicle_id;
        
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20013, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_available_bike_park;
END;
/

----Get all unavailable bikes in park

CREATE OR REPLACE FUNCTION getAllUnavailableBikeInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR AS
    cur_all_unavailable_bike_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_unavailable_bike_park 
        FOR 
        SELECT P.PARK_ID, V.vehicle_id,v.vehicle_status ,V.weight,v.aerodynamic_coef,v.frontal_area,B.wheel_size
        FROM VEHICLE V, BICYCLE B, PARK P
        WHERE P.PARK_ID=p_park_id AND V.PARK_ID=P.PARK_ID AND V.VEHICLE_STATUS = UPPER('UNAVAILABLE') AND V.vehicle_id=B.vehicle_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20014, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_unavailable_bike_park;
END;
/

----Get all scooters in park

CREATE OR REPLACE FUNCTION getAllScooterInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR AS
    cur_all_scooter_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_scooter_park FOR 
        SELECT V.vehicle_id,S.SCOOTER_TYPE, P.PARK_ID,v.vehicle_status ,V.weight, v.aerodynamic_coef, v.frontal_area,S.MAX_BATTERY,S.ACTUAL_BATTERY,S.motor,S.lock_time
        FROM VEHICLE V, SCOOTER S, PARK P
        WHERE P.PARK_ID=p_park_id AND V.PARK_ID=P.PARK_ID AND V.vehicle_id=S.vehicle_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20015, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_scooter_park;
END;
/

----Get all available scooters in park

CREATE OR REPLACE FUNCTION getAllAvailableScooterInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR AS
    cur_all_available_scooter_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_available_scooter_park FOR  
        SELECT  V.vehicle_id, S.SCOOTER_TYPE, P.PARK_ID, v.vehicle_status ,V.weight, v.aerodynamic_coef,v.frontal_area, S.MAX_BATTERY, S.ACTUAL_BATTERY,S.motor,S.lock_time
        FROM VEHICLE V, SCOOTER S, PARK P
        WHERE P.PARK_ID=p_park_id AND V.PARK_ID=P.PARK_ID AND V.VEHICLE_STATUS = UPPER('AVAILABLE') AND V.vehicle_id=S.vehicle_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20016, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_available_scooter_park;
END;
/

----Get all unavailable scooters in park

CREATE OR REPLACE FUNCTION getAllUnavailableScooterInPark(p_park_id VARCHAR) RETURN SYS_REFCURSOR AS
    cur_all_unavailable_scooter_park SYS_REFCURSOR;
    BEGIN
        OPEN cur_all_unavailable_scooter_park FOR 
       SELECT P.PARK_ID, V.vehicle_id,v.vehicle_status ,V.weight,v.aerodynamic_coef,v.frontal_area, S.SCOOTER_TYPE, S.MAX_BATTERY, S.ACTUAL_BATTERY,S.motor,S.lock_time
        FROM VEHICLE V, SCOOTER S, PARK P
        WHERE P.PARK_ID=p_park_id AND V.PARK_ID=P.PARK_ID AND V.VEHICLE_STATUS = UPPER('UNAVAILABLE') AND V.vehicle_id=S.vehicle_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20017, 'ERROR, NO PARK FOUND IN THE DATABASE');
        END IF;
    RETURN cur_all_unavailable_scooter_park;
END;
/

------------------------------------------------------------------TRIP----------------------------------------------------

----Add a Trip

CREATE OR REPLACE PROCEDURE addTrip(g_username VARCHAR, g_vehicle_id VARCHAR, g_park_id VARCHAR, g_ending_park VARCHAR, g_start_time TIMESTAMP)
AS
max_id INTEGER;
BEGIN
    SELECT MAX(trip_id) INTO max_id from TRIP ;
    IF max_id IS NULL THEN max_id:=1;
    ELSE max_id := max_id+1;
    END IF;
    INSERT INTO trip VALUES(max_id, g_username, g_vehicle_id, g_park_id, g_ending_park, g_start_time, null);
    IF SQL % NOTFOUND 
    THEN RAISE_APPLICATION_ERROR(-20019, 'ERROR, NO PARK FOUND IN THE DATABASE');
    END IF;
END;
/
----Get start time from a trip

CREATE OR REPLACE FUNCTION getStartTime (p_trip_id INTEGER) RETURN TIMESTAMP AS
    g_start_time TIMESTAMP;
    BEGIN
        SELECT T.START_TIME INTO g_start_time
        FROM TRIP T WHERE T.trip_id = p_trip_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20018, 'ERROR, NO TRIP FOUND IN THE DATABASE');
        END IF;
    RETURN g_start_time;
END ;
/

----Get end time from a trip

CREATE OR REPLACE FUNCTION getEndTime (p_trip_id INTEGER) RETURN TIMESTAMP AS
    g_end_time TIMESTAMP;
    BEGIN
        SELECT T.END_TIME INTO g_end_time
        FROM TRIP T WHERE T.trip_id = p_trip_id;
        IF SQL % NOTFOUND
            THEN  RAISE_APPLICATION_ERROR(-20019, 'ERROR, NO TRIP FOUND IN THE DATABASE');
        END IF;
    RETURN g_end_time;
END;
/

----Get all trips

CREATE OR REPLACE FUNCTION getAllTrips(g_username VARCHAR) RETURN SYS_REFCURSOR
AS
    cur_all SYS_REFCURSOR;
    BEGIN
        OPEN cur_all FOR
        SELECT * FROM TRIP
            WHERE ENDING_PARK IS NOT NULL AND END_TIME IS NOT NULL AND USERNAME = g_username;
    RETURN cur_all;
END;
/


------------------------------------------------------------------PATHS----------------------------------------------------

----Add a path

CREATE OR REPLACE PROCEDURE addPaths(latA NUMBER, longA NUMBER, latB NUMBER, longB NUMBER, g_kinetic_coefficient NUMBER, g_wind_speed NUMBER, g_wind_direction INTEGER)
AS
    cur_location1 varchar(100);
    cur_location2 VARCHAR(100);
    BEGIN
            SELECT id_location  INTO cur_location1 FROM LOCATIONS
            WHERE LOCATIONS.LAT = latA AND LOCATIONS.LONGI = longA;

            SELECT id_location INTO cur_location2 FROM LOCATIONS
            WHERE LOCATIONS.LAT = latb AND LOCATIONS.LONGI = longB;

            INSERT INTO PATHS VALUES (cur_location1, cur_location2, g_kinetic_coefficient, g_wind_direction, g_wind_speed);
END;
/

----Get a path between two points

CREATE OR REPLACE FUNCTION getPaths(g_id_1 VARCHAR, g_id_2 VARCHAR) RETURN SYS_REFCURSOR
AS
cur_paths SYS_REFCURSOR;
    BEGIN
        OPEN cur_paths FOR
            SELECT * FROM PATHS
            WHERE ID_LOCATION1 = g_id_1 AND ID_LOCATION2 = g_id_2 OR ID_LOCATION2 = g_id_1 AND ID_LOCATION1 = g_id_2;
        RETURN cur_paths;
END;
/

-----------------------------------------------

CREATE OR REPLACE FUNCTION getParkbyCoord(p_latitude NUMBER,p_longitude NUMBER) RETURN VARCHAR
AS
   var_id             VARCHAR(100);

  BEGIN
  SELECT id_location into var_id
  from Park p
  INNER JOIN locations l  ON p.park_id=l.id_location 
  WHERE l.longi=p_longitude AND l.lat=p_latitude;
  
RETURN var_id;
END;
/

----------------------------------------------

CREATE OR REPLACE FUNCTION parkDisponibilityBike(p_park_id VARCHAR) RETURN INTEGER AS
bike_count INTEGER;
capacity INTEGER;
    BEGIN
        SELECT COUNT(*) INTO bike_count
        FROM VEHICLE v, PARK p INNER JOIN BICYCLE b ON (VEHICLE_ID = b.VEHICLE_ID)
        WHERE p.PARK_ID=p_park_id;

        SELECT p.BIKE_CAPACITY INTO capacity FROM PARK p
        WHERE p.PARK_ID = p_park_id;

        IF SQL%NOTFOUND 
            THEN RAISE_APPLICATION_ERROR(-20019,'Error, PARK_ID not found');
        END IF;
        
        IF(bike_count < capacity)
            THEN RETURN capacity - bike_count;
        END IF;
    RETURN 0;
END;
/

--------------------------

CREATE OR REPLACE FUNCTION parkDisponibilityScooter(p_park_id VARCHAR) RETURN INTEGER AS
    scooter_count INTEGER;
    capacity INTEGER;
    BEGIN
        SELECT COUNT(*) INTO scooter_count
        FROM VEHICLE v, PARK p INNER JOIN SCOOTER s  ON (VEHICLE_ID = s.VEHICLE_ID)
        WHERE p_park_id = p.PARK_ID;

        SELECT p.SCOOTER_CAPACITY INTO capacity FROM PARK p
        WHERE p.PARK_ID = p_park_id;
        
        IF SQL%NOTFOUND 
        THEN RAISE_APPLICATION_ERROR(-20020,'Error, PARK_ID not found');
        END IF;
        
        IF(scooter_count < capacity)
        THEN RETURN capacity - scooter_count;
            END IF;
            
            RETURN 0;
    END;
/

-------------------------------------------------------------------
CREATE OR REPLACE FUNCTION getTripId(p_vehicleId VARCHAR) RETURN INTEGER AS

tripId INTEGER;

BEGIN SELECT T.trip_id INTO tripId
FROM trip T
WHERE T.vehicle_id=p_vehicleId AND T.end_time is null;
        IF SQL%NOTFOUND
            THEN RAISE_APPLICATION_ERROR(-20025,'Error, Vehicle_ID not found');
        END IF;
return tripId;

   END;
/

-----------------------------------

CREATE OR REPLACE PROCEDURE endTrip(p_tripId INTEGER ,p_latitude NUMBER,p_longitude NUMBER,p_endDate timestamp)
AS

var_id   VARCHAR(100);

  BEGIN
  SELECT id_location into var_id
  from Park p
  INNER JOIN locations l  ON p.park_id=l.id_location
  WHERE l.longi=p_longitude AND l.lat=p_latitude;


    UPDATE trip SET TRIP.ending_park = var_id
    WHERE TRIP.trip_id LIKE p_tripId;
    
    UPDATE trip SET TRIP.end_time = p_endDate
    WHERE TRIP.trip_id LIKE p_tripId;

IF SQL%NOTFOUND 
        THEN RAISE_APPLICATION_ERROR(-20026,'Error');
        END IF;
   END;
/

-------------------------------------------

CREATE OR REPLACE PROCEDURE updateUserPoints(g_username VARCHAR,g_points INTEGER)
IS
    BEGIN
        UPDATE REGISTERED_USERS SET REGISTERED_USERS.POINTS = g_points
        WHERE REGISTERED_USERS.USERNAME =g_username;
END;
/

-----------------------------------------------------------------------

CREATE OR REPLACE FUNCTION getTrip(p_tripID integer)RETURN SYS_REFCURSOR
AS
    cur_getTrip SYS_REFCURSOR;
        BEGIN
        OPEN cur_getTrip FOR 
        SELECT *
        from trip
        where trip.trip_id=p_tripID;
        

    IF SQL%NOTFOUND THEN RAISE_APPLICATION_ERROR(-20027, 'ERROR, NO TRIP WITH THAT ID');
    END IF;

    RETURN cur_getTrip;
    
END;
/

---------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE updateLocTime(g_scooterID VARCHAR, g_loc_time timestamp)
IS
    BEGIN
        UPDATE scooter SET scooter.lock_time = g_loc_time
        WHERE scooter.vehicle_id=g_scooterID;
END;
/

------Get all Paths

CREATE OR REPLACE FUNCTION getAllPaths RETURN SYS_REFCURSOR
AS
    curAllPaths SYS_REFCURSOR;
    BEGIN
        OPEN curAllPaths FOR
        SELECT * FROM paths;
        RETURN curAllPaths;
END;
/

CREATE OR REPLACE FUNCTION getCoordsbyPark(g_parkID VARCHAR) RETURN SYS_REFCURSOR
AS
       cur_getCoords SYS_REFCURSOR;

BEGIN
OPEN cur_getCoords FOR
SELECT p.lat_park,p.long_park
from Park p
WHERE p.park_id=g_parkID;

RETURN cur_getCoords;
END;
/
