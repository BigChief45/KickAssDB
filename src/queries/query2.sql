SELECT P.person_name, V.model
FROM Person AS P, Vehicle AS V
WHERE P.vehicle_id = V.vehicle_id
AND P.person_name = 'John';

