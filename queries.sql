SELECT   DATEPART(YEAR, DateOccurred) AS 'Year',
          DATEPART(MONTH, DateOccurred) AS 'Month',
          DATEPART(DAY, DateOccurred) AS 'Day',
          COUNT(*) AS 'Impressions'
FROM      Impression
GROUP BY  DATEPART(DAY, DateOccurred),
         DATEPART(MONTH, DateOccurred),
         DATEPART(YEAR, DateOccurred)
ORDER BY  'Year',
         'Month',
         'Day'

