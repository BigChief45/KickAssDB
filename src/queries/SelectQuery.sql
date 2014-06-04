SELECT Count(*)
FROM Person, Vehicle
WHERE Person.vehicle_id <> Vehicle.vehicle_id
