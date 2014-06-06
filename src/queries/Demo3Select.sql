SELECT COUNT(*)
FROM Stopword, Abstract AS A 
WHERE Stopword.stopwordId = A.stopwordId
