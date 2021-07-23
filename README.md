# FormApp

Aplicación móvil para la materia de cómputo móvil, que se encarga de calcular diversas fórmulas.

Para la realización de esta app de prueba se utlizan los siguientes elementos:
- Fragments
- View Binding
- Bundles
- CardViews
- TextInputLayouts
- AutoCompleteTextView
- Spinner
- Soporte para dos lenguajes (Español e Inglés)

## SplashScreen

![Captura de Pantalla 2021-07-23 a la(s) 17 42 58](https://user-images.githubusercontent.com/21089847/126848069-acd587cf-a76d-473b-9211-66c007ece4cd.png)


## Menú principal
  
Se utiliza un spinner en modo dialogo (dialog), para seleccionar la formula que se deseas ocupar. En este caso se ocupa la formula de:
* Ley de gravitación universal.
* Volumen de prisma pentagonal.
* Ecuación de los gases ideales.  

![Captura de Pantalla 2021-07-23 a la(s) 16 50 58](https://user-images.githubusercontent.com/21089847/126844945-b9e0bba9-8aa3-4d5c-ac72-d03fbefb84d9.png)
![Captura de Pantalla 2021-07-23 a la(s) 16 54 00](https://user-images.githubusercontent.com/21089847/126845218-95acdf9c-973e-455a-8127-a125a40a8c3c.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 06 30](https://user-images.githubusercontent.com/21089847/126845948-28dc5095-2383-4a86-bdb3-326be7e2645e.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 07 52](https://user-images.githubusercontent.com/21089847/126846005-1d04f358-15ac-403a-8b7d-986d52fd4562.png)

### Sección de ecuación de gases ideales.

Para esta sección se utiliza la ecuación de los gases ideales como punto de partida. Para darle la opción al usuario de que pueda elegir la variable
que quiere despejar. La única variable que se excluye a despejar es <b>R</b> ya que es una constante para la fórmula.
Cada que se selecciona una variable distinta a despejar, se cambia los hints de cada EditText, y se hacen las validaciones necesarias para que las 
variables que se encuentren en el denominador no deban ser cero.  
![Captura de Pantalla 2021-07-23 a la(s) 17 21 40](https://user-images.githubusercontent.com/21089847/126846878-861a16f2-2e27-462a-aa03-ad1b18acb7f6.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 19 48](https://user-images.githubusercontent.com/21089847/126846761-50ec4025-4770-414a-bb7a-4f83f4e386a4.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 20 34](https://user-images.githubusercontent.com/21089847/126846824-ebe639f5-dbdd-48ea-aa00-30d4a97e18b6.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 21 04](https://user-images.githubusercontent.com/21089847/126846857-911a6cc3-510f-4851-8f12-ae0a0036de27.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 22 53](https://user-images.githubusercontent.com/21089847/126846934-bca00b3c-91fa-4088-8654-69da3d656b46.png)


## Validación de errores
  
![Captura de Pantalla 2021-07-23 a la(s) 16 52 19](https://user-images.githubusercontent.com/21089847/126845039-558e3e98-4ae3-4cb7-aac5-2e5322333819.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 08 57](https://user-images.githubusercontent.com/21089847/126846101-3012ea18-9c8f-4c74-ad22-f12f2b2b8702.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 11 11](https://user-images.githubusercontent.com/21089847/126846242-e448b59c-d560-45d8-aec1-420b4c645bd7.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 25 40](https://user-images.githubusercontent.com/21089847/126847130-1900b088-2a8c-456f-a67f-eb64ceda2f04.png)

## Resultados
  
![Captura de Pantalla 2021-07-23 a la(s) 17 41 35](https://user-images.githubusercontent.com/21089847/126848012-c44a859f-7d2b-4cd7-a073-d091b8451a6f.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 28 47](https://user-images.githubusercontent.com/21089847/126847286-3c5ed2d2-e624-4489-a5ee-f02ee25551da.png)
![Captura de Pantalla 2021-07-23 a la(s) 17 34 31](https://user-images.githubusercontent.com/21089847/126847628-39413402-a0ae-47a7-8767-3d031d2bcadf.png)






