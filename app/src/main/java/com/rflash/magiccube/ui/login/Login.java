package com.rflash.magiccube.ui.login;

import com.rflash.magiccube.http.BaseBean;

/**
 * Created by yangfan on 2017/11/27.
 */

public class Login extends BaseBean{


    /**
     * pointId : 1
     * prvKey : a90178e279f923dc45f0374648a75db4ce1c2e1fdf12fc5b83b1268af3d5a49466b33deefaa2505104251d42a8f4a7449557e6be57c2a52cc6d6db150e372392df91847ede03ad79f7d786cf4aa67a79d256286386fe868acaffd372724ad42406561d15907349ae45ff3899ad59bd038d351da4818535da896d61bb7396fc3d2d88ba0beda6493d545e0d81ae63095e13de27bb4d550a475c081bdaf7b1ee3fc1ed1d39942475abdd00d7c8547b27260c5fc952c98fc4f1d0934f5a960fb3935afc9547aa823692afcde3a91ffc007319090625cfee904cb1aa407cc4d46dbc31332d15466cdd1f692a61a190db71995469115b2c24b744d63754914b7c05762fc9a98c851013b53d79b5e9a16d7c1c3df3e369a32d441c7ccca05c33c22d584ca8223acdf83d68388d445158da91e8768ba3bcb8e774ce46e11bfd3705ba4a35712cd7b6487c04b7402abc0c67429db71b028beecafe09c60c1efc251e53063c66bf5f769805fb3972f71222578ef32afda98496bf09a1e8b58385b0aaa509af43b7ad3eac8e14caa6eb558432385566c28d6001f92202044bf6baa8cebdb3e33f419ebbda2c44d9244401e6350ec0112e453a7c00ae13bbfe58cb5d29ba96a790e39afa4e02d671ae69ac3d79b5eb0737aae7e81284521f574130a14a9e5fee8d2982f831b5ea1e755ad55b36e74cb064d2b6ee4d90296f6060e7d7eeba7d2b7b8760e72487945d30583bf55f9beaaa25714b527f94fd7ec8e14621c3114bcc546174341d0a0ab3505959fdade4a572baca2bb11c0e9546e6734de5ad7c7a34abe1fc0c16fd9088ac5570bda24c8a9d9faa4d9cd243f4af36b2a90261aa3c841925ed14b09726d40b07bed640e56064b14dca8f69e39d07b61b3cc396b98b3b0dddd8c8de2f3b7412a6066338053ca3f269ed43bc08683fc9ce2034fcab0606493a2964a99cae87d7cbeca09cb360c58e9b57233bf903d13d53f44e7fedb350da0dd5b6c3040ed055a75c79fa992c56194f6314c951f7e37a0cfaca1dc14bf0758f2e530ec7e566cba98e17c10336faa81fe4a34c5f6095f3ddfacd508bb00fbed8784d1ebceae919990338ea285724eaf84049fed2410caaa5968fb54b158969b4eb579a3e6e272cb27e2e752c8aef7c965af5d38d7f3e84c8bf745dc5de34a84e6a02111cc4d57efa7a1c023321de69b2dca28902ba18fbacbb0b3772e428a1ff6e4b8a3d33a9d95c0c1a80a783caf51afafb4a7ed86591813ea53d80e4
     * pubKey : 9be5c908c48590270045a3df09018a17a9d8e02b131a08e70cf6f0d1d1830c7a20c10739d8df798464018755aef153af0bb1a0c8a397f6854b2b8f9c1087ee7af8d66500882dee10b0ee66a76c20c42e6062637036088172eff3c70e3ccb26da210f36c17ca485115369fd6e9cfabb24e4c3f57d2eb9a2577b388065de727aa4c36d610f46280fe512f24a8ae35d022204841173983559a3a59657bbf530e10b1e4370ba062275a80032eaa54a95ad8ffbf212c293895a1befc4445d43f85bc2a2eeed1799d7d2d61c3bcf8b7f2271678bcb190034267307ea8719b3c36ad7e05867eb1a27bdb4e226d46a60279c57c394c7fb6d1276d87bef0b7470f353a06a916c94aac4f8279b7970773f9f9abd149f47a81f8fa4790fa3400c357d399e6a
     */

    private String pointId;
    private String prvKey;
    private String pubKey;
    private String posMachineCode;
    private String merchantCode;//商户号
    private String termCode;//养卡点的终端号

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermCode() {
        return termCode;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPrvKey() {
        return prvKey;
    }

    public void setPrvKey(String prvKey) {
        this.prvKey = prvKey;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPosMachineCode() {
        return posMachineCode;
    }

    public void setPosMachineCode(String posMachineCode) {
        this.posMachineCode = posMachineCode;
    }
}
