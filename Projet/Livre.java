package projetAppServeur;

public class Livre implements Document{
	private EtatLivre etatLivre;
	private Abonne abo;
	private int numero;
	
	@Override
	public int numero() {
		return this.numero;
	}

	@Override
	public void reserver(Abonne ab) throws PasLibreException {
		if(this.etatLivre.equals(EtatLivre.LIBRE)){
			this.abo = ab;
			this.etatLivre = EtatLivre.RESERVE;
		}else
		{
			throw new PasLibreException();
		}
	}

	@Override
	public void emprunter(Abonne ab) throws PasLibreException {
		if(this.etatLivre.equals(EtatLivre.LIBRE)){
			this.abo = ab;
			this.etatLivre = EtatLivre.EMPREINTE;
		}else
		{
			if(this.etatLivre.equals(EtatLivre.RESERVE) && this.abo.equals(ab)){
				this.etatLivre = EtatLivre.EMPREINTE;
			}
			else {
				throw new PasLibreException();
			}
		}
		
	}

	@Override
	public void retour() {
		this.abo = null;
		this.etatLivre = EtatLivre.LIBRE;
	}

}
