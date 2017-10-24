/*
 COPYRIGHT 2009 ESRI

 TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
 Unpublished material - all rights reserved under the
 Copyright Laws of the United States and applicable international
 laws, treaties, and conventions.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts and Legal Services Department
 380 New York Street
 Redlands, California, 92373
 USA

 email: contracts@esri.com
 */
//>>built
define("esri/nls/it/jsapi",({io:{proxyNotSet:"esri.config.defaults.io.proxyUrl non impostato."},map:{deprecateReorderLayerString:"Map.reorderLayer(/*String*/ id, /*Number*/ index) deprecato. Utilizzare Map.reorderLayer(/*Layer*/ layer, /*Number*/ index).",deprecateShiftDblClickZoom:"Map.(enable/disable)ShiftDoubleClickZoom deprecato. Shift-Double-Click zoom behavior non sarà supportato."},geometry:{deprecateToScreenPoint:"esri.geometry.toScreenPoint deprecato. Utilizzare esri.geometry.toScreenGeometry.",deprecateToMapPoint:"esri.geometry.toMapPoint deprecato. Utilizzare esri.geometry.toMapGeometry."},layers:{tiled:{tileError:"Impossibile caricare la tile"},dynamic:{imageError:"Impossibile caricare l´immagine"},graphics:{drawingError:"Impossibile disegnare l´elemento grafico "},agstiled:{deprecateRoundrobin:"Opzione costruttore ´roundrobin´ deprecata. Usare l´opzione ´tileServers´."},imageParameters:{deprecateBBox:"Proprietà ´bbox´ deprecata. Usare la proprietà ´extent´."},FeatureLayer:{noOIDField:"objectIdField non impostato [url: ${url}]",fieldNotFound:"Impossibile trovare il campo ´${field}´ nelle informazioni ´fields´ del livello [url: ${url}]",noGeometryField:"Impossibile trovare un campo di tipo ´esriFieldTypeGeometry´ nelle informazioni ´fields´ del livello. Se si utilizza un livello di map service, le feature non avranno geometria [url: ${url}]",invalidParams:"La query contiene uno o più parametri non supportati",updateError:"Errore durante l´aggiornamento del livello",createUserSeconds:"Creato da ${userId} qualche secondo fa",createUserMinute:"Creato da ${userId} un minuto fa",editUserSeconds:"Modificato da ${userId} qualche secondo fa",editUserMinute:"Modificato da ${userId} un minuto fa",createSeconds:"Creato qualche secondo fa",createMinute:"Creato un minuto fa",editSeconds:"Modificato qualche secondo fa",editMinute:"Modificato un minuto fa",createUserMinutes:"Creato da ${userId} ${minutes} minuti fa",createUserHour:"Creato da ${userId} un´ora fa",createUserHours:"Creato da ${userId} ${hours} ore fa",createUserWeekDay:"Creato da ${userId} ${weekDay} alle ore ${formattedTime}",createUserFull:"Creato da ${userId} il giorno ${formattedDate} alle ore ${formattedTime}",editUserMinutes:"Modificato da ${userId} ${minutes} minuti fa",editUserHour:"Modificato da ${userId} un´ora fa",editUserHours:"Modificato da ${userId} ${hours} ore fa",editUserWeekDay:"Modificato da ${userId} ${weekDay} alle ore ${formattedTime}",editUserFull:"Modificato da ${userId} il giorno ${formattedDate} alle ore ${formattedTime}",createUser:"Creato da ${userId}",editUser:"Modificato da ${userId}",createMinutes:"Creato ${minutes} minuti fa",createHour:"Creato un´ora fa",createHours:"Creato ${hours} ore fa",createWeekDay:"Creato il giorno ${weekDay} alle ore ${formattedTime}",createFull:"Creato il giorno ${formattedDate} alle ore ${formattedTime}",editMinutes:"Modificato ${minutes} minuti fa",editHour:"Modificato un´ora fa",editHours:"Modificato ${hours} ore fa",editWeekDay:"Modificato il giorno ${weekDay} alle ore ${formattedTime}",editFull:"Modificato il giorno ${formattedDate} alle ore ${formattedTime}"}},tasks:{gp:{gpDataTypeNotHandled:"Tipo di dati GP non gestito."},na:{route:{routeNameNotSpecified:"´RouteName´ non specificato per almeno 1 fermata nel FeatureSet delle fermate."}},query:{invalid:"Impossibile eseguire la query. Verificare i parametri."}},toolbars:{draw:{convertAntiClockwisePolygon:"I poligoni tracciati in senso antiorario verranno ridisegnati in senso orario.",addPoint:"Fare clic per aggiungere un punto",addShape:"Fare clic per aggiungere una forma o premere per iniziare e rilasciare per terminare",addMultipoint:"Fare clic per iniziare ad aggiungere punti",freehand:"Premere per iniziare e rilasciare per terminare",start:"Fare clic per iniziare a disegnare",resume:"Fare clic per continuare a disegnare",complete:"Fare doppio clic per completare",finish:"Fare doppio clic per terminare",invalidType:"Tipo di geometria non supportato"},edit:{invalidType:"Impossibile attivare lo strumento. Verificare che lo strumento sia valido per il tipo di geometria dato.",deleteLabel:"Elimina"}},virtualearth:{vetiledlayer:{bingMapsKeyNotSpecified:"BingMapsKey deve essere specificato."},vegeocode:{bingMapsKeyNotSpecified:"BingMapsKey deve essere specificato.",requestQueued:"Token server non recuperato. La richiesta di accodamento verrà eseguita dopo il recupero del token del server."}},widgets:{attributeInspector:{NLS_first:"Primo",NLS_previous:"Precedente",NLS_next:"Seguente",NLS_last:"Ultimo",NLS_deleteFeature:"Elimina",NLS_title:"Modifica attributi",NLS_errorInvalid:"Non valido",NLS_validationInt:"Il valore deve essere un numero intero.",NLS_validationFlt:"Il valore deve essere mobile.",NLS_of:"di",NLS_noFeaturesSelected:"Nessuna feature selezionata"},overviewMap:{NLS_drag:"Trascinare per modificare i limiti della mappa",NLS_show:"Mostra anteprima mappa",NLS_hide:"Nascondi anteprima mappa",NLS_maximize:"Ingrandisci",NLS_restore:"Ripristina",NLS_noMap:"´map´ non trovato nei parametri di input",NLS_noLayer:"La mappa principale non dispone di un livello di base",NLS_invalidSR:"Riferimento spaziale del livello dato non compatibile con la mappa principale",NLS_invalidType:"Tipo di livello non supportato. I tipi validi sono ´TiledMapServiceLayer´e ´DynamicMapServiceLayer´"},timeSlider:{NLS_first:"Primo",NLS_previous:"Precedente",NLS_next:"Seguente",NLS_play:"Riproduci/Pausa",NLS_invalidTimeExtent:"TimeExtent non specificato o in un formato non corretto."},attachmentEditor:{NLS_attachments:"Allegati:",NLS_add:"Aggiungi",NLS_none:"Nessuno",NLS_error:"Errore.",NLS_fileNotSupported:"Questo tipo di file non è supportato."},directions:{error:{notEnoughStops:"Immettere un´origine e una destinazione.",unknownStop:"Località ´<name>´ non trovata.",routeTask:"Impossibile creare il percorso per questi indirizzi.",locator:"Località non trovata.",invalidStopType:"Tipo di fermata non valido",locatorUndefined:"Impossibile eseguire la geocodifica inversa. URL del localizzatore non definito.",noAddresses:"Non sono stati restituiti indirizzi.",noStops:"Non sono state specificate fermate da aggiungere.",maximumStops:"È stato raggiunto il numero massimo di fermate"},time:{minute:"minuto",minutes:"minuti",hour:"ora",hours:"ore"},units:{KILOMETERS:{name:"chilometri",abbr:"km"},METERS:{name:"metri",abbr:"m"},MILES:{name:"miglia",abbr:"mi"},NAUTICAL_MILES:{name:"miglia nautiche",abbr:"nm"}},showOptions:"Mostra opzioni",hideOptions:"Nascondi opzioni",findOptimalOrder:"Trova ordine ottimale",returnToStart:"Torna all´inizio",addDestination:"Aggiungi destinazione",viewFullRoute:"Zoom a percorso completo",getDirections:"Ottieni indicazioni",reverseDirections:"Inverti indicazioni",print:"Stampa",printNotes:"Immettere qui le note",printDisclaimer:"Le indicazioni vengono fornite solo per la pianificazione e sono soggette alle <a href='http://www.esri.com/legal/software-license' target='_blank'>Condizioni d´uso Esri</a>. È necessario tenere conto delle condizioni effettive della strada, che potrebbero essere diverse da quelle delle indicazioni, nonché dei segnali stradali e delle limitazioni previste per legge. L´utente si assume tutti i rischi connessi all´utilizzo delle indicazioni."},editor:{tools:{NLS_attributesLbl:"Attributi",NLS_cutLbl:"Taglia",NLS_deleteLbl:"Elimina",NLS_extentLbl:"Limiti",NLS_freehandPolygonLbl:"Poligono a mano libera",NLS_freehandPolylineLbl:"Polilinea a mano libera",NLS_pointLbl:"Punto",NLS_polygonLbl:"Poligono",NLS_polylineLbl:"Polilinea",NLS_reshapeLbl:"Modifica forma",NLS_selectionNewLbl:"Nuova selezione",NLS_selectionAddLbl:"Aggiungi a selezione",NLS_selectionClearLbl:"Cancella selezione",NLS_selectionRemoveLbl:"Elimina da selezione",NLS_selectionUnionLbl:"Unione",NLS_autoCompleteLbl:"Completamento automatico",NLS_unionLbl:"Unione",NLS_rectangleLbl:"Rettangolo",NLS_circleLbl:"Cerchio",NLS_ellipseLbl:"Ellisse",NLS_triangleLbl:"Triangolo",NLS_arrowLbl:"Freccia",NLS_arrowLeftLbl:"Freccia sinistra",NLS_arrowUpLbl:"Freccia su",NLS_arrowDownLbl:"Freccia giù",NLS_arrowRightLbl:"Freccia destra",NLS_undoLbl:"Annulla",NLS_redoLbl:"Ripeti"}},Geocoder:{main:{clearButtonTitle:"Cancella ricerca",searchButtonTitle:"Cerca",geocoderMenuButtonTitle:"Cambia geocodificatore",geocoderMenuHeader:"Seleziona geocodificatore",geocoderMenuCloseTitle:"Chiudi menu",untitledGeocoder:"Geocodificatore senza titolo"},esriGeocoderName:"Geocodificatore mondiale Esri"},HistogramTimeSlider:{NLS_range:"Intervallo",NLS_cumulative:"Cumulativo",NLS_play:"Riproduci/Pausa",NLS_invalidTimeExtent:"TimeExtent non specificato o in un formato non corretto.",NLS_overview:"PANORAMICA",NLS_range:"intervallo completo"},legend:{NLS_points:"Punti",NLS_lines:"Linee",NLS_polygons:"Poligoni",NLS_creatingLegend:"Creazione legenda",NLS_noLegend:"Nessuna legenda"},popup:{NLS_moreInfo:"Altre informazioni",NLS_searching:"Ricerca in corso",NLS_prevFeature:"Oggetto feature precedente",NLS_nextFeature:"Oggetto feature seguente",NLS_close:"Chiudi",NLS_prevMedia:"Media precedente",NLS_nextMedia:"Media seguente",NLS_noInfo:"Nessuna informazione disponibile",NLS_noAttach:"Nessun allegato trovato",NLS_maximize:"Ingrandisci",NLS_restore:"Ripristina",NLS_zoomTo:"Zoom a",NLS_pagingInfo:"(${index} di ${total})",NLS_attach:"Allegati"},measurement:{NLS_distance:"Distanza",NLS_area:"Area",NLS_location:"Posizione",NLS_resultLabel:"Risultato misurazione",NLS_length_miles:"Miglia",NLS_length_kilometers:"Chilometri",NLS_length_feet:"Piedi",NLS_length_meters:"Metri",NLS_length_yards:"Iarde",NLS_area_acres:"Acri",NLS_area_sq_miles:"Miglia quadre",NLS_area_sq_kilometers:"Chilometri quadri",NLS_area_hectares:"Ettari",NLS_area_sq_yards:"Iarde quadre",NLS_area_sq_feet:"Piedi quadri",NLS_area_sq_meters:"Metri quadri",NLS_deg_min_sec:"DMS",NLS_decimal_degrees:"Gradi",NLS_map_coordinate:"Coordinate mappa",NLS_longitude:"Longitudine",NLS_latitude:"Latitudine"},bookmarks:{NLS_add_bookmark:"Aggiungi segnalibro",NLS_new_bookmark:"Senza titolo",NLS_bookmark_edit:"Modifica",NLS_bookmark_remove:"Rimuovi"},print:{NLS_print:"Stampa",NLS_printing:"Stampa in corso",NLS_printout:"Stampa"},templatePicker:{creationDisabled:"La creazione delle feature è disabilitata per tutti i layer.",loading:"Caricamento in corso..."}},arcgis:{utils:{baseLayerError:"Impossibile caricare il livello della mappa di base",geometryServiceError:"Specificare un servizio di geometria per aprire la mappa Web."}},identity:{lblItem:"elemento",title:"Accesso",info:"Effettuare l´accesso per accedere all´elemento su ${server} ${resource}",lblUser:"Nome utente:",lblPwd:"Password:",lblOk:"OK",lblSigning:"Accesso in corso...",lblCancel:"Annulla",errorMsg:"Nome utente/password non validi. Riprovare.",invalidUser:"Il nome utente o la password immessi non sono validi.",forbidden:"Il nome utente e la password sono validi, ma non si dispone di accesso alla risorsa.",noAuthService:"Impossibile accedere al servizio di autenticazione."},common:{cancel:"Annulla",ok:"OK",create:"Crea",close:"Chiudi",done:"Fine",apply:"Applica",remove:"Rimuovi",open:"Apri",edit:"Modifica",share:"Condividi",save:"Salva",help:"Guida",warning:"Avviso",deleteLabel:"Elimina",titleLabel:"Titolo:",newLabel:"Nuovo",arcgis:"ArcGIS",previous:"Precedente",submit:"Invia",next:"Seguente",yesLabel:"Sì",noLabel:"No",errorTitle:"Errore",upload:"Carica",sum:"Somma",minimum:"Minimo",maximum:"Massimo",average:"Media",standardDev:"Deviazione standard",statistic:"Statistica",attribute:"Attributo",selectAttribute:"Seleziona attributo",runAnalysis:"Analizza",oneLabel:"1.",twoLabel:"2.",threeLabel:"3.",fourLabel:"4.",outputnameMissingMsg:"Nome di output obbligatorio",miles:"Miglio/a",kilometers:"Chilometro/i",meters:"Metro/i",feet:"Piede/i",degree:"Grado/i decimale/i",inches:"Pollice/i",nautMiles:"Miglio/a nautico/che",pointsUnit:"Punto/i",yards:"Iarda/e",comingSoonLabel:"Disponibile a breve"},analysisTools:{performAnalysis:"Esegui analisi",summarizeData:"Riepiloga dati",findLocations:"Trova località",aggregateTool:"Aggrega punti",bufferTool:"Dati buffer",dataEnrichment:"Data Enrichment",analyzePatterns:"Analizza modelli",useProximity:"Usa prossimità",manageData:"Gestisci dati",aggregateToolName:"Aggrega punti",bufferToolName:"Crea buffer",summarizeWithin:"Riepiloga entro",sumnearby:"Riepiloga nelle vicinanze",createBuffers:"Crea buffer",driveTimes:"Crea poligoni tempo di guida",findExistingLocations:"Trova località esistenti",findNewLocations:"Deriva nuove località",geoenrichLayer:"Arricchisci feature",findRoute:"Trova percorso",findClosestFacility:"Trova più vicine",generateFleetPlan:"Genera piano itinerari per flotta",findHotSpots:"Trova hot spot",createDensitySurface:"Crea superficie di densità",correlationReporter:"Esplora correlazioni",createInterpolatedSurface:"Crea superficie",attributeCalculator:"Calcolatore attributi",overlayLayers:"Sovrapponi layer",mergeLayers:"Unisci layer",dissolveBoundaries:"Dissolvi confini",extractData:"Estrai dati",orgUsrMsg:"Per eseguire il servizio, è necessario essere membro di un´organizzazione.",pubRoleMsg:"L´account online non è stato assegnato al ruolo Editore.",servNameExists:"È già presente un risultato con questo nome. Rinominarlo e inviare nuovamente l´analisi.",outputLayerLabel:"Nome layer risultati",outputFileName:"Nome file di output",emptyResultInfoMsg:"The result of your analysis did not return any features. No layer will be created."},aggregatePointsTool:{aggregateDefine:"Conta <b>${layername}</b> entro",outputLayerName:"Aggregazione di ${pointlayername} in ${polygonlayername}",groupByLabel:"Scegli attributo in base a cui raggruppare (facoltativo)",itemDescription:"Feature service generato dall´esecuzione di soluzioni Aggrega punti. I punti del file CSV ${pointlayername} sono stati aggregati in ${polygonlayername}",itemTags:"Analisi, Aggrega punti, ${pointlayername}, ${polygonlayername}",itemSnippet:"Feature service Analisi generato da Aggrega punti",removeAttrStats:"Rimuovi statistiche attributi",keepPolygonLabel:"Mantieni poligoni senza punti",addStatsLabel:"Aggiungi statistiche (facoltativo)",chooseAreaLabel:"Scegli area"},findHotSpotsTool:{hotspotsPolyDefine:"Analizza <b>${layername}</b> per trovare hot spot e cold spot statisticamente rilevanti di ",hotspotsPointDefine:"Analizza <b>${layername}</b> per trovare hot spot e cold spot statisticamente rilevanti ",fieldLabel:"con o senza un campo di analisi",noAnalysisField:"Senza campo di analisi",hotspots:"Hot spot",outputLayerName:"Hot spot ${layername}",Options:"Opzioni",defineBoundingLabel:"Definisci punti in cui sono possibili incidenti",provideAggLabel:"Specifica aree di aggregazione per sommare incidenti",defaultBoundingOption:"Seleziona aree di confine",defaultAggregationOption:"Seleziona aree di aggregazione",itemDescription:"Feature service generato dall´esecuzione della soluzione Trova hot spot.",itemTags:"Analisi, Hot spot, ${layername}, ${fieldname}",itemSnippet:"Feature service Analisi generato da Trova hot spot",chooseAttributeLabel:"Scegli un campo di analisi",blayerName:"Confini segnati"},overlayLayersTool:{overlayDefine:"Sovrapponi <b>${layername}</b> a",chooseOverlayLayer:"Scegli layer per sovrapposizione",chooseOverlayMethod:"Scegli metodo di sovrapposizione",itemDescription:"Feature service generato dall´esecuzione della soluzione Sovrapponi layer.",itemTags:"Analisi, Sovrapponi layer, ${layername}",itemSnippet:"Feature service Analisi generato da Sovrapponi layer",unionOutputLyrName:"unione di ${layername} e ${overlayname}",intersectOutputLyrName:"intersezione di ${layername} e ${overlayname}",eraseOutputLyrName:"cancella ${layername} con ${overlayname}",overlayLayerPolyMsg:"Il layer di sovrapposizione deve essere di tipo poligono per la sovrapposizione di tipo unione",notSupportedEraseOverlayMsg:"Layer di sovrapposizione non supportato per la sovrapposizione di tipo cancellazione. Verrà utilizzata la sovrapposizione di tipo intersezione.",intersect:"Incrocia",union:"Unione",erase:"Cancella"},bufferTool:{bufferDefine:"Crea buffer da <b>${layername}</b>",outputLayerName:"Buffer di ${layername}",sizeLabel:"Immetti dimensione buffer",sizeHelp:"Per creare più buffer, immettere le distanze separate da spazi (2 3 5.5).",typeLabel:"Tipo di buffer",resultLabel:"Nome layer risultati",optionsLabel:"Opzioni",itemDescription:"Feature service generato dall´esecuzione della soluzione Feature buffer. I dati di input di ${layername} sono stati memorizzati nel buffer a una distanza di ${distance_field} ${units}",itemTags:"Buffer, ${layername}",itemSnippet:"Feature service Analisi generato da Buffer",overlap:"Sovrapponi",dissolve:"Dissolvi",include:"Includi",exclude:"Escludi",around:"Intorno a",sideType:"Tipo di lato",endType:"Tipo finale",left:"Sinistra",right:"Destra",round:"Arrotondato",flat:"Piatto",multipleDistance:"I buffer per più distanze devono essere",rings:"Anelli",disks:"Dischi",areaofInputPoly:"Poligoni dell´area di input in poligoni buffer",distanceMsg:"Sono consentiti solo valori numerici",distance:"Distanza",field:"Campo"},driveTimes:{toolDefine:"Crea aree intorno a <b>${layername}</b>",outputLayerName:"Guida da ${layername}: ---",measureLabel:"Misura:",measureHelp:"Per ottenere più aree per ogni punto, digitare le dimensioni separate da spazi (2 3.5 5)",areaLabel:"Aree da punti diversi:",trafficLabel:"Usa condizioni del traffico (facoltativo)",resultLabel:"Nome layer risultati",itemDescription:"Feature service generato dall´esecuzione della soluzione Crea poligoni tempo di guida.",itemTags:"Tempi di guida, ${layername}",itemSnippet:"Feature service Analisi generato da Crea poligoni tempi di guida"},extractDataTool:{layersToExtract:"Layer da estrarre",studyArea:"Area di studio",outputDataFormat:"Formato dati di output",filegdb:"Geodatabase file",shpFile:"Shapefile",lyrpkg:"Layer package",selectFtrs:"Seleziona feature",clipFtrs:"Feature di taglio",sameAsDisplay:"Come visualizzato",sameAsLayer:"Come ${layername}",outputfileName:"NomeOutput.zip",itemDescription:"File generato dall´esecuzione della soluzione Estrai dati.",itemTags:"Analisi, Estrai dati",itemSnippet:"Elemento del file di analisi generato da Estrai dati"}}));