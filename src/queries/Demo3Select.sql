SELECT Sum(freq)
FROM Stopword, Abstract
WHERE Stopword.stopwordId = Abstract.stopwordId
 
