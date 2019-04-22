package com.pruebaFalabella;

import java.awt.image.ReplicateScaleFilter;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class carritoCompra {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File chromeDriverFile = new File("drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.getAbsolutePath());
		WebDriver driver = new ChromeDriver();
		
		// Ingresar a pagina www.google.cl
		driver.get("https://www.google.cl");
		driver.manage().window().maximize();
		
		// Ingresar a falabella
		WebElement txtBuscadorGoogle = driver.findElement(By.name("q"));
		txtBuscadorGoogle.sendKeys("Falabella");
		txtBuscadorGoogle.sendKeys(Keys.ENTER);
		driver.findElement(By.partialLinkText("Falabella.com - Mejor Compra Online")).click();
		
		// Buscar producto y agregar a la bolsa
		WebElement buscadorFalabella = driver.findElement(By.id("searchQuestionSolr"));
		Thread.sleep(3000);
		buscadorFalabella.sendKeys("PS4");
		buscadorFalabella.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Consola PS4")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(),'Agregar a la bolsa')]")).click();
		Thread.sleep(3000);
		
		// Ver bolsa de compras
		driver.findElement(By.partialLinkText("VER BOLSA DE COMPRAS")).click();
		Thread.sleep(3000);
		
		// Aumentar a 2 productos
		driver.findElement(By.xpath("//button[contains(text(),'+')]")).click();
		Thread.sleep(3000);
		WebElement cantidad=driver.findElement(By.xpath("//button[@class='fb-quantity-input__minus']/following-sibling::input"));
		cantidad.getAttribute("value");
		int cantidades = Integer.parseInt(cantidad.getAttribute("value"));
		System.out.println("Cantidad de productos: "+cantidad.getAttribute("value"));
		
		// Agregar garantia extendida por 2 años
		driver.findElement(By.xpath("//a[@class='fb-inline-dropdown__link js-inline-dropdown__link']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@data-value='2578489']")).click();
		Thread.sleep(3000);
		
		
		// Presionar botón "Ir a comprar"
		driver.findElement(By.xpath("//div[@class='fb-basket__order-container__summary']//button[1]")).click();;
		Thread.sleep(5000);
		
		// Precio del producto
		WebElement precioProducto=driver.findElement(By.xpath("//div[@class='fbra_orderSummary__itemHeading fbra_test_orderSummary__itemHeading']/span[2]"));
		System.out.println("Precio del producto: "+precioProducto.getText());
		String preProducto = precioProducto.getText();
		preProducto = preProducto.replace("$","");
		preProducto = preProducto.replace(".","");
		
		//Precio garantia extendida
		WebElement garantiaExtendida=driver.findElement(By.xpath("//div[@class='fbra_test_orderSummary__extraItemwarranty fbra_orderSummary__extraItemwarranty']/span[2]"));
		System.out.println("Precio de garantia extendida por producto: "+garantiaExtendida.getText());
		String garantiaExt = garantiaExtendida.getText();
		garantiaExt = garantiaExt.replace("$", "");
		garantiaExt = garantiaExt.replace(".", "");
		
		// Monto final a pagar
		WebElement montoFinalElement=driver.findElement(By.xpath("//span[@class='fbra_text fbra_test_orderSummary__totalCostPrice fbra_orderSummary__totalCostPrice']"));
		System.out.println("Monto final a pagar: "+montoFinalElement.getText());
		String montoFinalPagar = montoFinalElement.getText();
		montoFinalPagar = montoFinalPagar.replace("$", "");
		montoFinalPagar = montoFinalPagar.replace(".", "");
		int montoFinal = Integer.parseInt(montoFinalPagar);
				

		// Validación precio reflejado en pagina vs precio productos/garantia x cantidad
		int producto = Integer.parseInt(preProducto);
		int garantia = Integer.parseInt(garantiaExt);
		int totalProductos = producto * cantidades;
		int totalGarantia = garantia * cantidades;
		int precioTotal = totalProductos + totalGarantia;
	
		if (precioTotal!=montoFinal) throw new Exception ("Precio calculado inconsistente con precio total reflejado en pagina");
		
		driver.quit();
				
	}

}
