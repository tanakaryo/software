curl -X POST "http://localhost:18080/realms/demo/protocol/openid-connect/token" \
--data "grant_type=client_credentials&client_secret=DrRgXpwPziwfhSiHZNZ7qA3A9Zx7pPnn&client_id=demo-app"

eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzTUwtZW1jMmlnS2szdVczN2wxTFdWbkc0Mmh2NlVRaU9sN3BYTFdQajRrIn0.eyJleHAiOjE3Mjg2NzI2MjcsImlhdCI6MTcyODY3MjMyNywianRpIjoiYjk2Y2Y2MzYtNWFlYS00NmU4LWI1NmMtZjM4ODMyMmM3YmMxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxYjE2OTcxZC00ZDVhLTQ0NGItYTE5OC1kMjQyZmUzYjY4ODUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vLWFwcCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLWRlbW8iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY2xpZW50SWQiOiJkZW1vLWFwcCIsImNsaWVudEhvc3QiOiIxOTIuMTY4LjY1LjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtZGVtby1hcHAiLCJjbGllbnRBZGRyZXNzIjoiMTkyLjE2OC42NS4xIn0.C8uVwvEMhpLcRPXvvDUVfkfToP27jbloW-ufXRqs_xjbGivS7kUqL8sd5UHHALp2rfNIQFShbB8vXgpSC-ZcHQR-noy0TjsayLlndCSNIzyJOtLfZdw6kQb5st7jkL2XLQauGwsjdGrPjpvL2QWIlZI367G1A5XJj3siTQzLH_NQsnNTIJUEfpFKqv9aPUZMdl3kmM08P8uAcDbs5Fx_mPIsNFII1P6z4Ki6NR4Igk1at9vneqjB-XRwVnBD1NVDV0H2qL85AEwnxeDVIG7cRoweLJs35hGoAhgSOjpBP9xf7H4Ow8l1cdZEgmpqE92FOdBTY_2djVzBigs-maED6A

curl -v -X GET -H "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzTUwtZW1jMmlnS2szdVczN2wxTFdWbkc0Mmh2NlVRaU9sN3BYTFdQajRrIn0.eyJleHAiOjE3Mjg2NzM1NDgsImlhdCI6MTcyODY3MzI0OCwianRpIjoiNGFhZDViYTgtYzI1Ny00MDU2LTlkNzItZDZlZTY2ZDA0YTc4IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxYjE2OTcxZC00ZDVhLTQ0NGItYTE5OC1kMjQyZmUzYjY4ODUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vLWFwcCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJkZWZhdWx0LXJvbGVzLWRlbW8iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImRlbW8tYXBwIjp7InJvbGVzIjpbInVtYV9wcm90ZWN0aW9uIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiZGVtby1hcHAiLCJjbGllbnRIb3N0IjoiMTkyLjE2OC42NS4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWRlbW8tYXBwIiwiY2xpZW50QWRkcmVzcyI6IjE5Mi4xNjguNjUuMSJ9.f-Dh-JZHQ_e5KuQHuNxciX7_J2WP6VycIo9jChyEJphphLTNo-XHfAnXXs5pOZ3lGylBZMDwdCl4ar6V0kDluFTo_To0pfjAKao8MSgJZuNbbVXqc-CYYAPJWXL5NDzyLwF5e1yONg3Mg-w_0lfCKiHcaTCfoR6W9Tky1yF45otyE8BB6GyytiVywfwCSxL8A5HNPHJZbeP-GArdFNn4pMz80F51Yoff9rW75gB1ndBXEHEoFrQ1mlB8KQ99TVg84OUsanBQd2kpl8wckHEpnRYftAkdhAlmj7vUGDPK8FtWu9tAZ3qLko56xWHDYMNp7RoKHwzPbjxkaaY2S_NKcA" http://localhost:8080/list

curl -v -X GET -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJncVpLanlES0VZMktydVplZEFmeG43VUdTR19qemVrM3FUY3RNT3NZeWdJIn0.eyJleHAiOjE3Mjg3MzA1OTUsImlhdCI6MTcyODczMDI5NSwianRpIjoiZjcxNDU1ZDUtNDJlMi00Njg1LWE0OWEtYTk4ZjhhOTEwOTI1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiMzkwMTkxZi00NGJkLTRhZWQtOTc2MS1lNTM0NThkZjAzMGIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vLWFwcCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtZGVtbyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImRlbW8tYXBwIjp7InJvbGVzIjpbInVtYV9wcm90ZWN0aW9uIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiZGVtby1hcHAiLCJjbGllbnRIb3N0IjoiMTkyLjE2OC42NS4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWRlbW8tYXBwIiwiY2xpZW50QWRkcmVzcyI6IjE5Mi4xNjguNjUuMSJ9.AdIMh-Q2mwLY2ACTY8j0iyHIddeJ6jEet7K-2L7NUPNWzLUIsuSg8TgI_VNrMJJNs8rKbtI7el_SNAnZH1liZLTIF03weWqYUIEFyJKMmAPW4PeFQ4o-cs0uh9zTA_eWSlnf1pVwadWEs9c6FynK5atFjxLTKKjbmk2n0Aw-OGkauTjyGfHqt3AiHZW3h92C01Fs3HBx9dlm6CTyEl2DXd42bn1HR-JstmPCsHRhqJUVTrq4SY7OL3ibVYfodTjuK223skOnWiyc8p37JnPx3LDARxNFMOnY61nTz4rr4XA7EGFJLxWQdWoH7evL5PWz50c_SCqeCsC0faRNCBRyyA" http://localhost:8080/list




curl -X POST "http://localhost:18080/realms/demo/protocol/openid-connect/token" \
--data "grant_type=client_credentials&client_secret=uxykxBuBu98JPQJ41QrW0VAkBBOnzKQz&client_id=demo2"

eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJncVpLanlES0VZMktydVplZEFmeG43VUdTR19qemVrM3FUY3RNT3NZeWdJIn0.eyJleHAiOjE3Mjg3MzAyNDIsImlhdCI6MTcyODcyOTk0MiwianRpIjoiZmZjN2RjNDEtNGQ1My00M2UzLWFkY2QtMzdjYTIwZmMyMmRmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJkMTdkODMyOC1kZjNjLTQ3YjUtYTljNi01OGFjNWIyYTdiYWUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMiIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtZGVtbyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfSwiZGVtbzIiOnsicm9sZXMiOlsidW1hX3Byb3RlY3Rpb24iXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiZGVtbzIiLCJjbGllbnRIb3N0IjoiMTkyLjE2OC42NS4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWRlbW8yIiwiY2xpZW50QWRkcmVzcyI6IjE5Mi4xNjguNjUuMSJ9.AUOqTo0kbNpBdklwDLJDqBhnBJViaIXTmROwEBhrYTRafiEDCvvjyffsPE3d37vYserkTnc4AOznCFZ9KSNPUiw6rTSwYFxEAwvUa1UUt_dA5sZCW-X_DiuNjpLl0h657OOXtCBvzNxYw9G_tF9PvE7be7KhPNgfX_O0OExlzYE1PEINo-A2Qyy4JhREtxRJeCsIje9NQoCFZVrCAH1C8XYoJpYfwuwnn6UGoSDAmLp4hrhvkzqT7Gx0c8dqa4ylODyhkIuyr3lqnrnyv7KQal37MP3RfqHl54R5g1OzeG7oJGaoVEX0XR9p66CItiX6t6LaHFMl-4McZ_18eMelfw

curl -v -X GET -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJncVpLanlES0VZMktydVplZEFmeG43VUdTR19qemVrM3FUY3RNT3NZeWdJIn0.eyJleHAiOjE3Mjg3NjQ0MDQsImlhdCI6MTcyODc2NDEwNCwianRpIjoiOGI2OWJmMzYtMzMyYy00ZWJmLTk5MzEtZTk5Y2QzOTZjNjY3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDoxODA4MC9yZWFsbXMvZGVtbyIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJkMTdkODMyOC1kZjNjLTQ3YjUtYTljNi01OGFjNWIyYTdiYWUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJkZW1vMiIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtZGVtbyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfSwiZGVtbzIiOnsicm9sZXMiOlsidW1hX3Byb3RlY3Rpb24iXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudElkIjoiZGVtbzIiLCJjbGllbnRIb3N0IjoiMTkyLjE2OC42NS4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWRlbW8yIiwiY2xpZW50QWRkcmVzcyI6IjE5Mi4xNjguNjUuMSJ9.dmYn6Au1MnfrVloJ7n9lPtgZhXhUDMWxpoIcCVOYaLN7A4ZhFWtK333z9lNAw9gS-hGzTxc-UlxLla7bABaIBlXsibDL0lkEGEySpksLRLul995YmBjgKu6mACYQqWSCcFI6-hwCPag0K6ODGyxtHXgvqgVZRXqhoqoIcU3jtT-JwJBZxAqKDPzgdY4rHeEZ7HvKopd76iFlXky1E1_Fs2pJmszfY9vm5zEn1FxmI4t1suWOL1AP_O6YQMCjpiqtAYIJuzcBnc6fXQ0W2r2o79Soty3xkGS96hqcR_peEkyiYDkh9EULTuOsoZv6apay6cad9Oh0zWJd6i5GpxTCfg" http://localhost:8080/list
