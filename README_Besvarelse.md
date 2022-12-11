# PGR301-2022 DevOps
* [![CI pipeline](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/ci.yml/badge.svg)](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/ci.yml)
* [![Push Docker image to ECR](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/docker.yml/badge.svg)](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/docker.yml)
* [![Terraform CloudWatch](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/ShevinMamandi/DevOps_PGR301_exam-1041/actions/workflows/cloudwatch_dashboard.yml)
## DEL1

### Beskriv med egne ord;
### Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring
### av DevOps kunne være med på å løse disse? 

### Svar:
Basert på scenarioen som ble beskrevet i Oppgaveteksten “ShopiFly” har flere utfordringer
i utviklingsprosessen.
Første utfordringen, de har ikke noen deployment mekanisme. De deployer veldig sjelden og alt skjer manuelt.
Andre er, flyten av utviklingsprosessen er ikke kontinuerlig. alt skjer lokalt og systemet går i produksjon manuelt.
Tredje er pga dårlig mekanisme nye oppgavene(tasks) blir ikke tatt over fordi utviklerne er opptatte
med å fikse feilene som er i forrige versjon som ble rullet tilbake.

Med riktig innføring av DevOps kan “ShopiFly” løse flere problemer som de har i dag,
for eksempel med å automatisere utviklingsprosessen. I dag flere bedrifter kan release koden flere
ganger i løpet av en dag på grunn av automatiseringen. I tillegg det ser ut at det er mangel på
flere utviklingsmiljøer(dev, test, qa, produksjon) derfor alltid det er feil i systemet eller feilen blir oppdaget sent.
Å ha kommunikasjon mellom teamene og selskapet kan hjelpe å lage et miljø  som alle sidene kan forstår
hverandre bedre og utføre riktige avgjørelser. Det hjelper utviklerne til å få feedback fra kunden og forstå deres
behov. Selskapet har utført flere ting men fortsatt har de problemer.
Med bedre kommunikasjon mellom utviklingsteamet og selskapet kunne de velge en riktig mekanisme for
å løse dette problemet.

### Hvilke DevOps prinsipper blir brutt?
### Svar:
Jeg synes det er brutt på følgende prinsipper:
- Automation of the software development lifecycle
- Collaboration and communication
- Continuous improvement and minimization of waste
- Hyperfocus on user needs with short feedback loops

### En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det
### mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet
### med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?

### Svar:
Sette opp flere miljøer og alt skal gjennom Dev, Test, QA og produksjon.  
da trenger seleskapet tversfaglig team som har kunnskap om både vanlig utvikling,testing og devops eksperter. 
I tillegg de må ha  Code Review på Pullrequestene. 
Det betyr at når en utvikler lager en PR må tagge minst 2 utviklere til å se gjennom koden og 
utviklerne må kommentere koden hvis de finner noe som er usikker. Når alle kommentarene er resolved da 
kan pullRequesten godkjennes og pushes til Dev-miljø.

I DevOps perspektiv de mangler flere nødvendige miljøer som
- Dev: er for utviklere

- Test: er der testerne skal teste koden som ble deployet av utviklere

- QA(Kontroll punkt): et miljø som alt skal være fungerende før koden blir pushet til produksjon

- Prod: er et miljø som systemet går i live (produksjon)

### Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er
### utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få
### ved at team han ansvar for både drift- og utvikling?

### Svar:
Å ha et team som kan gjøre både drift og utviklingen hjelper mye ShopiFly. 
Grunnen til dette er at teamet får bedre oversikt over utviklingsprosessen og de vet hvor flytten ligger. 
Tenk om teamet har ulike utviklingsmiljøer og hvis koden pushes til dev og videre til test da er det 
lettere å ta i folk hvis noen problem dukker opp midt i mellom. I tillegg er det billigere å ha et team som er 
tversfaglig enn å ha 2 ulike team.

### Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan
### bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveraner.
### Svar:
Det er veldig vanskelig å ha et svar for dette spørsmålet. Med automatisere (automation) deployment prosesser 
kan utviklerne bruke mer tid på kode kvaliteten og feilen som oppstår. Kommunikasjon mellom kunden 
og utviklerne kan hjelpe også mye til å redusere risikoen ved leveransen. De kan analysere og finne ut en 
riktig tidspunkt til å release koden. Tanken er finne ut et tidspunkt som systemet har minst mulig trafikk på. 
I dags vi ser at flere systemer kommer med nye release midt på natten eller i helgene som de har veldig lite 
trafikk på. 
I tillegg skal det være et tidspunkt i sprinten hvor utviklerne (noen av de) kan angripe rask feilene som 
dukker opp i release tiden.

## DEL2
## Oppgave 3

### Branch protection og status sjekker - Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at
### - Ingen kan pushe kode direkte på main branch
### - Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning
### - Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.

### Svar:

1. I Github gå til repositoriet og gå til Settings
2. Velg Branches  (see bilde)
3.  Velg Add  branch protection rule(see bilde)

![Alt text](/Del2-opp3.PNG)

4. Branch name pattern: Main
5. Velg følgende:
   - require a pull request before merging
   - Require status check to pass before merging 🡪I søkefeltet skriv inn teksten build som 
     skal la deg velge "GitHub Actions". (Nå kan vi ikke merges en pull request inn i Main uten at status er ok. 
   - Do not allow bypassing the above settings.
6. Trykk på Create.

## Del 3-Docker
### Oppgave 1
### Hvorfor feiler workflowen?
Fordi på logg inn stegen på pipelinen i workflowen prøver å logge inn på Docker hub men den mangler 
brukernavn og passord til å gjør det. (SE BILDET NEDOVER)

![Alt text](/Del3-opp1.PNG)

### Beskriv hva du må gjøre for å få workflow til å fungere med din DockerHub konto?
### Svar:
Jeg må lage access token key i docker hub og gå til github og lage der lage GitHub Actions Secrets 
med access token og bruker navn fra Docker hub.

### Oppgave 3
### Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo:
### Svar:

1. kjør disse kommandoene:
```
docker build . --tag <give the image a name>
```
```
docker run <tag_name>
```
logg inn  på docker med denne kommandoen:
```
docker login
docker tag <tag> <dockerhub_username>/<tag_remote>
docker push <username>/<tag_remote>
```
Etter dette får du taggen i docker hub.

2. Gå til Cloud 9 miljø og kjør denne kommandoen(du auroriserer ditt docker inn i cloud 9 miljø ):
```
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 244530008913.dkr.ecr.eu-west-1.amazonaws.com
```
3. kjør denne kommandoen( du pusher container image til ECR repository) :
```
docker build -t <ditt tagnavn> .
docker tag <ditt tagnavn> 244530008913.dkr.ecr.eu-west-1.amazonaws.com/<ditt ECR repo navn>
docker push 244530008913.dkr.ecr.eu-west-1.amazonaws.com/<ditt ECR repo navn>
```
4. For github actions du må lage AWS secret key id og AWS secret key.

5. legge AWS secret key id og AWS secret key den til dit repo secrets på github.

6. du må endre tag name og ECR repository name på docker.yml.
7. Hver gang du kjører den pusher Image til ECR.

## DEL5
### Oppgave 1

### Se på provider.tf filen
### Froklar med egene or. Hva er årsaken til dette problemet?
### Svar:
Amazon S3-bucketNnavn må være unike globalt. Hvis du får feilmeldingen "bucketnavn eksisterer allerede" 
eller "BucketAlreadyExists", må du bruke et annet bucketnavn for å lage bucket. Disse feilmeldingene indikerer 
at en annen AWS-konto eier en bucket med samme navn.

### Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?
### Svar:
Den mangler backend delen på provider filen, som oppretter terraform state-filen inni bucketen. 
Og fordi stateFilen har informssjon om bucketen som allerede eksistere, og når man kjørrer “init” 
kommandoen prøver å overskrive den bucketen som er definert i state-filen. 
Om den ikke eksistere da prøver terraform å lage ny bucket med et unik/ navn som ikke allerede eksistere.















