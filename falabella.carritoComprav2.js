import { Selector} from 'testcafe';

fixture `New Fixture`
    .page `www.google.cl`;

test('New Test', async t => {
    await t
        .pressKey('capslock')
        .typeText(Selector('#tsf').find('[name="q"]'), 'Falabella')
        .pressKey('enter')

        .expect(Selector('.r').find('h3').textContent).contains('Falabella.com - Mejor Compra Online')

        .click(Selector('.r').find('h3').withText('Falabella'))
        .click(Selector('#searchQuestionSolr'))
        .pressKey('capslock')
        .typeText(Selector('#searchQuestionSolr'), 'PS4')
        .pressKey('enter')
        .click(Selector('.section__pod-top-title').find('div').withText('Consola PS4 1TB FIFA 19'))

        .expect(Selector('.fb-product-cta--desktop').find('h1').textContent).contains('Consola PS4 1TB FIFA 19')

        .click(Selector('button').withText('AGREGAR A LA BOLSA')) // Goole Chrome
        //.click(Selector('button').withText('Agregar a la bolsa')) // Internet Explorer
        .click(Selector('#fb-modal-add').nth(1).find('a').withText('VER BOLSA DE COMPRAS')) // Goole Chrome
        //.click(Selector('#fb-modal-add').nth(1).find('a').withText('Ver Bolsa de Compras')) // Internet Explorer
        .click(Selector('.fb-quantity-input__plus'))
        

        // precio producto
        .expect(Selector('.fb-pod__internet-prices__from').textContent).contains('$ 349.990')
 
        // cantidad productos
        .expect(Selector('.fb-quantity-input__field').getAttribute('value')).contains('2')
        
        .click(Selector('.fb-inline-dropdown__link.js-inline-dropdown__link'))
        .click(Selector('a').withText('2 Años'))
        .click(Selector('.fb-order-status__cta').nth(1).find('button').withText('IR A COMPRAR')) // Goole Chrome
        //.click(Selector('.fb-order-status__cta').nth(1).find('button').withText('Ir a comprar')); // Internet Explorer
   
        // precio garantia
        .expect(Selector('span').withText('59.590').textContent).contains('$59.590')

         // precio total
        .expect(Selector('[class^="fbra_text fbra_test_orderSummary__totalCostPrice f"]').textContent).contains('$819.160')

        
});