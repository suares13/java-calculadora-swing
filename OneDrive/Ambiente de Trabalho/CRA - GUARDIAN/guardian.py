import os
import datetime

def gerar_header():
    print("-" * 60)
    print("🛡️ CRA-GUARDIAN: AUDITOR DE CONFORMIDADE DIGITAL")
    print(f"Data da análise: {datetime.datetime.now().strftime('%d/%m/%Y %H:%M')}")
    print("-" * 60)

def extrair_sbom(arquivo_req="requirements.txt"):
    """
    gera o sbom (software bill of materials) baseado nas dependências.
    o sbom é um requisito central da cra para transparência.
    """
    print("\n📦 GERANDO SBOM (LISTA DE INGREDIENTES)...")
    if not os.path.exists(arquivo_req):
        print(f"⚠️ erro: '{arquivo_req}' não encontrado. crie o arquivo para listar dependências.")
        return []
    
    with open(arquivo_req, "r") as f:
        dependencias = [line.strip() for line in f if line.strip() and not line.startswith("#")]
    
    for dep in dependencias:
        print(f"  [+] componente identificado: {dep}")
    
    return dependencias

def auditoria_cra():
    """
    realiza o checklist de obrigações da cra, focando no ciclo de vida e design.
    """
    print("\n🔍 INICIANDO AUDITORIA DE CONFORMIDADE (CRA)...")
    perguntas = [
        "1. o software foi desenvolvido com práticas de segurança desde o design (ex: SAST/DAST)?",
        "2. existe um plano de suporte para todo o ciclo de vida esperado do produto?",
        "3. há um processo ativo de gestão e publicação de patches de vulnerabilidades?",
        "4. a documentação técnica para o usuário final é transparente e clara?"
    ]
    
    pontos = 0
    for p in perguntas:
        resp = input(f"  {p} (s/n): ").lower()
        if resp == 's':
            pontos += 25
            
    return pontos

def gerar_relatorio(sbom, score):
    print("\n" + "=" * 60)
    print("📊 RELATÓRIO FINAL DE CONFORMIDADE")
    print("=" * 60)
    print(f"ITENS NO SBOM: {len(sbom)}")
    print(f"SCORE DE PRONTIDÃO CRA: {score}%")
    
    if score == 100:
        print("✅ STATUS: PRONTO PARA O MARCADO CE DIGITAL")
    elif score >= 50:
        print("⚠️ STATUS: CONFORMIDADE PARCIAL - REQUER AJUSTES NO CICLO DE VIDA")
    else:
        print("❌ STATUS: NÃO CONFORME - RISCO DE PENALIDADES NA UE")
    print("=" * 60)

if __name__ == "__main__":
    gerar_header()
    meu_sbom = extrair_sbom()
    if meu_sbom:
        resultado = auditoria_cra()
        gerar_relatorio(meu_sbom, resultado)