import { VistaTiendaPage } from './app.po';

describe('vista-tienda App', function() {
  let page: VistaTiendaPage;

  beforeEach(() => {
    page = new VistaTiendaPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
